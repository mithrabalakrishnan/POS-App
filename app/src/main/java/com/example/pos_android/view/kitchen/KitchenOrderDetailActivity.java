package com.example.pos_android.view.kitchen;



import android.os.Bundle;

import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.KitchenOrderDetailResponse;
import com.example.pos_android.databinding.ActivityKitchenOrderDetailBinding;
import com.example.pos_android.view.BaseActivity;

public class KitchenOrderDetailActivity extends BaseActivity implements KitchenOrderDetailContract.View  {
  ActivityKitchenOrderDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        showSnackBar(binding.getRoot(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(KitchenOrderDetailActivity.this, message);
    }
    @Override
    public void showKitchenOrderDetailApiSuccess(KitchenOrderDetailResponse saveResponse) {

    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}