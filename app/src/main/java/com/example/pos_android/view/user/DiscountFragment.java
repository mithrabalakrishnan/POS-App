package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.pos_android.view.login.LoginActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

        binding.ivBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_couponFragment_to_summaryFragment);
        });
    }


    @Override
    public void onItemClick(Integer position, String from,Boolean isView ) {
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
    public void showAddVoucherApiResponseSuccess(String response) {

    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void showAllVouchers(GetVoucherResponse response) {
        try {
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println("Today's date is "+dateFormat.format(cal.getTime()));

            couponsData.clear();
            for (int i = 0; i < response.getData().size(); i++) {
                String offerDate = response.getData().get(i).getDate();
                if (dateFormat.parse(offerDate).after(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))) {
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
            if(couponsData.isEmpty()){
                binding.noCouponTxt.setVisibility(View.VISIBLE);
                binding.recyclerViewCoupon.setVisibility(View.GONE);
            }
            else{
                binding.noCouponTxt.setVisibility(View.GONE);
               binding.recyclerViewCoupon.setVisibility(View.VISIBLE);
            }


        } catch (ParseException e1) {
            Log.d("Date exception", "showAllVouchers: " + e1.toString());
        }
    }
}