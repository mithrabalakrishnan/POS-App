package com.example.pos_android.view.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_android.contracts.VoucherContract;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.databinding.ActivityAddVoucherBinding;
import com.example.pos_android.presenter.VoucherPresenter;
import com.example.pos_android.utils.Validation;
import com.example.pos_android.view.BaseActivity;

public class AddVoucherActivity extends BaseActivity implements VoucherContract.View {
    private ActivityAddVoucherBinding binding;
    private VoucherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        presenter = new VoucherPresenter(this,this);
        binding.iconBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnAdd.setOnClickListener(v -> {
            validateFields();
        });
    }

    private void validateFields() {
        if (!Validation.isNotNullOrEmpty(binding.txtName.getText().toString())) {
            binding.textLayoutName.setError("Please enter title");
        } else if (!Validation.isNotNullOrEmpty(binding.txtCategory.getText().toString())) {
            binding.txtLayoutCategory.setError("Please enter voucher code");
            binding.textLayoutName.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.txtPrice.getText().toString())) {
            binding.txtPrice.setError("Please enter discount");
            binding.txtLayoutCategory.setError(null);
        } else {
            presenter.callAddVoucher(binding.txtName.getText().toString(),
                    binding.txtCategory.getText().toString(),binding.txtPrice.getText().toString());
        }
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

    }

    @Override
    public void showWarningMessage(String message) {
        showToast(this, message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void showAddVoucherApiResponseSuccess(CommonResponse response) {
        hideProgressBar();
        showToast(this,response.getMessage());
        finish();
    }

    @Override
    public void showInputWarning() {

    }
}