package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.example.pos_android.R;
import com.example.pos_android.contracts.FoodReservationContract;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.data.room.CartDatabase;
import com.example.pos_android.databinding.FragmentPaymentBinding;
import com.example.pos_android.presenter.ConfirmOrderPresenter;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

public class PaymentFragment extends BaseFragment implements FoodReservationContract.View {
    ConfirmOrderPresenter presenter;
    private FragmentPaymentBinding binding;
    private OrderInfoModel orderInfoModel;
    private CartDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        database = CartDatabase.getAppDatabase(requireContext());
        orderInfoModel = PaymentFragmentArgs.fromBundle(getArguments()).getOrderInfo();
        presenter = new ConfirmOrderPresenter(this, requireContext());
        binding.buttonDebitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etCard.getText().toString().isEmpty()) {
                    if (binding.etCard.getText().toString().length() >= 16) {
                        if (!binding.etExpiry.getText().toString().isEmpty()) {
                            if (!binding.etCvv.getText().toString().isEmpty()) {
                                presenter.doFoodReservation(orderInfoModel);
                            } else binding.etCvv.setError("Please enter CVV");
                        } else binding.etExpiry.setError("Please enter expiry");
                    } else binding.etCard.setError("Please enter 16 digit number");
                } else binding.etCard.setError("Please enter card no");
            }
        });


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
    public void showTableOrderSuccessResponse(String message) {
        database.orderDao().deleteAll();
        showWarningMessage(message);
        Navigation.findNavController(requireView()).navigate(R.id.action_paymentFragment_to_orderSuccessFragment);
    }
}