package com.example.pos_android.view.admin;

import android.os.Bundle;
import android.view.View;

import com.example.pos_android.contracts.AddKitchenUserContract;
import com.example.pos_android.databinding.ActivityAddKitchenBinding;
import com.example.pos_android.view.BaseActivity;

public class AddKitchenActivity extends BaseActivity implements AddKitchenUserContract.View {
    private ActivityAddKitchenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddKitchenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
    }

    private void initUi() {
        binding.ivBack.setOnClickListener(view -> {
                onBackPressed();

        });
        binding.btnAddKitchenUser.setOnClickListener(view -> {
                if(binding.etFirstName.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter firstname");
                }
                else if(binding.etLastName.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter last name");
                }
                else if(binding.etUsername.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter user name");
                }
                else if(binding.etEmail.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter user email");
                }
                else if(binding.etMobile.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter user mobile number");
                }
                else if(binding.etPassword.getText().toString().trim().isEmpty()){
                    showWarningMessage("Please enter password");
                }
                else{

                }
            }
        );
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showInputWarning() {

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
        showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(this, message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}