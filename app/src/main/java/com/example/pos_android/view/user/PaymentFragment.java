package com.example.pos_android.view.user;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;

import com.example.pos_android.R;
import com.example.pos_android.contracts.FoodReservationContract;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.RecaptchaVerifyResponse;
import com.example.pos_android.data.model.food.FoodOrderResponseModel;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.data.room.CartDatabase;
import com.example.pos_android.databinding.FragmentPaymentBinding;
import com.example.pos_android.presenter.ConfirmOrderPresenter;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.Objects;

public class PaymentFragment extends BaseFragment implements FoodReservationContract.View {
    ConfirmOrderPresenter presenter;
    private FragmentPaymentBinding binding;
    private OrderInfoModel orderInfoModel;
    private CartDatabase database;
    int SMS_PERMISSION_CODE = 300;
    boolean hasPermission = false;
    private SmsManager smsManager;

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
        smsManager = SmsManager.getDefault();
        checkPermissions();
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
    public void showTableOrderSuccessResponse(FoodOrderResponseModel tableReservationResponse) {
        database.orderDao().deleteAll();
         ArrayList<String> phoneNumberArray = tableReservationResponse.data.phone;
        for(int i = 0; i< phoneNumberArray.size();i++){
            String message = "Hi Kitchen, Your order with id " + tableReservationResponse.data.foodOrderedItemModel.orderid + " Is " + tableReservationResponse.data.foodOrderedItemModel.getStatus() + ". \nThanks from " + getResources().getString(R.string.app_name);
            Log.d("Update Status", message);
            try {
                smsManager.sendTextMessage(phoneNumberArray.get(i), null, message, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        showWarningMessage(tableReservationResponse.message);
        Navigation.findNavController(requireView()).navigate(R.id.action_paymentFragment_to_orderSuccessFragment);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            hasPermission = true;
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Checking whether user granted the permission or not.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Showing the toast message
            // showToast(AddFoodActivity.this, "Permission granted");
            hasPermission = true;
        } else {
            hasPermission = false;
            //  showToast(AddFoodActivity.this, "Permission denied");
        }
    }
    private void showAlertWithButton(@NonNull String title, @NonNull String message, @NonNull String buttonMessage) {
        new AlertDialog.Builder(requireContext()).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonMessage, null).create().show();
    }

}