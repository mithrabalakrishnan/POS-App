package com.example.pos_android.view.kitchen;

import android.os.Bundle;

import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.data.model.kitchen.KitchenOrderResponse;
import com.example.pos_android.databinding.ActivityKitchenBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.view.BaseActivity;

public class KitchenActivity extends BaseActivity implements KitchenListingContract.View {
   ActivityKitchenBinding binding;
   private KitchenPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter =new KitchenPresenter(this,this);
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
        showToast(KitchenActivity.this, message);
    }

    @Override
    public void showKitchenOrderListApiSuccess(KitchenOrderResponse saveResponse) {


    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}