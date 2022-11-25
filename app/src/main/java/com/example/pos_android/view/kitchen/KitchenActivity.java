package com.example.pos_android.view.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.data.model.KitchenOrderResponse;
import com.example.pos_android.databinding.ActivityKitchenBinding;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.admin.SalesReportActivity;

public class KitchenActivity extends BaseActivity implements KitchenListingContract.View {
   ActivityKitchenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenBinding.inflate(getLayoutInflater());
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