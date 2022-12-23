package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.adapter.HistoryAdapter;
import com.example.pos_android.contracts.HistoryContract;
import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentOrderBinding;
import com.example.pos_android.presenter.HistoryPresenter;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

import java.util.ArrayList;

public class OrderFragment extends BaseFragment implements HistoryContract.View {
    ArrayList<HistoryResponse.FoodOrderList> historyList = new ArrayList<>();
    private FragmentOrderBinding binding;
    private HistoryPresenter presenter;
    private HistoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        showShimmer();
        presenter = new HistoryPresenter(this, requireContext());
        presenter.getHistory();
    }

    @Override
    public void showProgressBar() {
       // showLoadingDialog(requireContext());
    }

    @Override
    public void hideProgressBar() {
      //  hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        hideShimmer();
        hideLoadingDialog();
        if (string.equals("HTTP 401 ")) {
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.clear();
            showToast(requireContext(), "Session expired");
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finishAffinity();
        } else
            showToast(requireContext(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(requireContext(), message);
    }

    @Override
    public void showResponse(HistoryResponse response) {
        hideShimmer();
        if(response.getData().getFoodOrderList().size()== 0){
            binding.popularRecyclerview.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
        }
        else{
            binding.popularRecyclerview.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
        }
        // showToast(requireContext(), response.getMessage());
        historyList.clear();
        adapter = new HistoryAdapter(response.getData().getFoodOrderList(), requireContext());
        binding.popularRecyclerview.setAdapter(adapter);
        binding.popularRecyclerview.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private void showShimmer() {
        binding.popularRecyclerview.setVisibility(View.GONE);
        binding.layoutOrderItemShimmer.setVisibility(View.VISIBLE);
        binding.layoutOrderItemShimmer.startShimmer();


    }
        private void hideShimmer() {
        binding.popularRecyclerview.setVisibility(View.VISIBLE);
        binding.layoutOrderItemShimmer.setVisibility(View.GONE);
        binding.layoutOrderItemShimmer.stopShimmer();


    }
}