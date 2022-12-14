package com.example.pos_android.view.waiter;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.R;
import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.KitchenUpdateStatusResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityWaiterOrderDetailBinding;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

public class WaiterOrderDetailActivity extends BaseActivity implements KitchenOrderDetailContract.View {
 ActivityWaiterOrderDetailBinding binding;
    KitchenResponse.KitchenData kitchenData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWaiterOrderDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        kitchenData = (KitchenResponse.KitchenData) getIntent().getSerializableExtra("data");

        binding.tvOrderId.setText("Order ID : #" + kitchenData.getId());
        binding.tvDate.setText(kitchenData.getDate());
        binding.tvTime.setText(kitchenData.getTime());
        binding.tvTableId.setText(kitchenData.getTableId().toString());
        binding.tvCustomerDetails.setText(String.format("%s\n%s", kitchenData.getUsername(), kitchenData.getUserPhoneNumber()));
        binding.txtFoodItems.setText(String.format("%s", kitchenData.getFoodName()));
        binding.txtFoodQty.setText(String.format("x %s", kitchenData.getQuanty()));

        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    @Override
    public void showProgressBar() {
        showLoadingDialog(this);
    }

    @Override
    public void hideProgressBar() {
        hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        if (string.equalsIgnoreCase(getResources().getString(R.string.unauthorized))) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.clear();
            showToast(this, "Session expired");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        } else
            showSnackBar(binding.getRoot(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        if (message != null) {
            showToast(WaiterOrderDetailActivity.this, message);
        }
    }

    @Override
    public void showSuccessResponse(KitchenUpdateStatusResponse saveResponse) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}