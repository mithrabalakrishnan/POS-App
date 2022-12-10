package com.example.pos_android.view.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pos_android.R;
import com.example.pos_android.adapter.CouponsAdapter;
import com.example.pos_android.contracts.VoucherContract;
import com.example.pos_android.data.model.CouponsData;
import com.example.pos_android.data.model.GetVoucherResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentDiscountBinding;
import com.example.pos_android.presenter.VoucherPresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DiscountFragment extends BaseFragment implements OnItemClickListener, VoucherContract.View {
    private FragmentDiscountBinding binding;
    private SessionManager sessionManager;
    private VoucherPresenter presenter;
    private List<CouponsData> couponsData = new ArrayList<>();
    private CouponsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiscountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        presenter = new VoucherPresenter(this, requireContext());
        sessionManager = new SessionManager(requireContext());
        adapter = new CouponsAdapter(couponsData, requireContext(), this);
        binding.recyclerViewCoupon.setHasFixedSize(true);
        binding.recyclerViewCoupon.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewCoupon.setAdapter(adapter);

        presenter.getAllVoucher();
    }


    @Override
    public void onItemClick(Integer position, String from) {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_couponFragment_to_summaryFragment);
    }

    @Override
    public void showProgressBar() {
        showLoadingDialog(requireContext());
    }

    @Override
    public void hideProgressBar() {
        hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        showToast(requireContext(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(requireContext(), message);
    }

    @Override
    public void showAddVoucherApiResponseSuccess(String response) {

    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void showAllVouchers(GetVoucherResponse response) {
        couponsData.clear();
        for (int i = 0; i < response.getData().size(); i++) {
            Random rand = new Random();
            CouponsData data = new CouponsData(
                    response.getData().get(i).getVoucherTitle(),
                    response.getData().get(i).getDate(),
                    response.getData().get(i).getVoucherCode(),
                    presenter.voucherImages.get(rand.nextInt(presenter.voucherImages.size())),
                    Integer.parseInt(response.getData().get(i).getVoucherDiscount()
                    )
            );
            couponsData.add(data);
            adapter.notifyDataSetChanged();
        }
    }
}