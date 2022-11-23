package com.example.pos_android.view.register;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.contracts.RegisterContract;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityRegisterBinding;
import com.example.pos_android.presenter.RegisterPresenter;
import com.example.pos_android.utils.Validation;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

import java.util.Objects;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

    }

    private void initView() {
        registerPresenter = new RegisterPresenter(this, this);
        sessionManager = new SessionManager(this);
        binding.ivBack.setOnClickListener(v -> onBackPressed());
        binding.btnSignUp.setOnClickListener(v -> validateFieldsAndSignUp());

    }

    private void validateFieldsAndSignUp() {
        if (!Validation.isNotNullOrEmpty(Objects.requireNonNull(binding.etFirstName.getText()).toString())) {
            binding.firstNameLayout.setError("Please enter first name");
        } else if (!Validation.isNotNullOrEmpty(binding.etLastName.getText().toString())) {
            binding.lastNameLayout.setError("Please enter last name");
            binding.firstNameLayout.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.etUsername.getText().toString())) {
            binding.usernameLayout.setError("Please enter username");
            binding.firstNameLayout.setError(null);
        } else if (!Validation.isValidEmail(binding.etEmail.getText().toString())) {
            binding.emailLayout.setError("Please enter valid email-id");
            binding.usernameLayout.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.etMobile.getText().toString())) {
            binding.mobileLayout.setError("Please enter mobile between 5-10");
            binding.emailLayout.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.etPassword.getText().toString())) {
            binding.passwordLayout.setError("Please enter valid password");
            binding.mobileLayout.setError(null);
        } else if (!Validation.isValidPassword(binding.etPassword.getText().toString())) {
            binding.passwordLayout.setError("Please enter minimum 6 characters for password");
        } else if (!Validation.isPasswordSecure(binding.etPassword.getText().toString())) {
            binding.passwordLayout.setError("Password must contain mix of upper and lower case letters as well as digits and one special character");
        } else if (!Validation.isPasswordConfirm(binding.etPassword.getText().toString(), binding.etConfirmPass.getText().toString())) {
            binding.confirmPasswordLayout.setError("Password confirmation failed");
            binding.passwordLayout.setError(null);
        } else {
            binding.confirmPasswordLayout.setError(null);
            registerPresenter.doRegister(
                    binding.etFirstName.getText().toString().trim(),
                    binding.etLastName.getText().toString().trim(),
                    binding.etUsername.getText().toString().trim(),
                    binding.etMobile.getText().toString().trim(),
                    binding.etEmail.getText().toString().trim(),
                    binding.etPassword.getText().toString().trim()
            );
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
        showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(this, message);
    }

    @Override
    public void showSuccess(String message) {
        showToast(this, message);
        sessionManager.setLogin(true);
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finishAffinity();
    }
}