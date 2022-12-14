package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.R;
import com.example.pos_android.contracts.AddWaiterContract;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityAddWaiterBinding;
import com.example.pos_android.presenter.AddWaiterPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

public class AddWaiterActivity extends BaseActivity implements AddWaiterContract.View {
    ActivityAddWaiterBinding binding;
    AddWaiterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWaiterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
    }

    private void initUi() {
        presenter = new AddWaiterPresenter();
        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();

        });

        binding.btnAddWaiter.setOnClickListener(view -> {
                    if (binding.etFirstName.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter firstname");
                    } else if (binding.etLastName.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter last name");
                    } else if (binding.etUsername.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter user name");
                    } else if (binding.etEmail.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter user email");
                    } else if (binding.etMobile.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter user mobile number");
                    } else if (binding.etPassword.getText().toString().trim().isEmpty()) {
                        showSnackBar(binding.getRoot(), "Please enter password");
                    } else {
                        presenter.addWaiter(binding.etFirstName.getText().toString(),
                                binding.etLastName.getText().toString(),
                                binding.etUsername.getText().toString(),
                                binding.etMobile.getText().toString(),
                                binding.etEmail.getText().toString(),
                                binding.etPassword.getText().toString()
                        );
                    }
                }
        );
    }

    @Override
    public void showSuccess(String response) {
        showWarningMessage(response);
        finish();
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