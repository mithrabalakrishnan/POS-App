package com.example.pos_android.view.login;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.contracts.LoginContract;
import com.example.pos_android.data.model.LoginResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityLoginBinding;
import com.example.pos_android.presenter.LoginPresenter;
import com.example.pos_android.utils.Validation;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.admin.AdminHomeActivity;
import com.example.pos_android.view.kitchen.KitchenActivity;
import com.example.pos_android.view.user.UserHomeActivity;

import java.util.Objects;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginPresenter presenter;
    private ActivityLoginBinding binding;
    private SessionManager sessionManager;
    private boolean isAdmin = false;
    private boolean isKitchen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        // getSupportActionBar().hide();

        presenter = new LoginPresenter(this, this);
        sessionManager = new SessionManager(this);
        binding.tvSignup.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        binding.buttonLogin.setOnClickListener(v -> {
            validateFields();
        });
    }

    private void validateFields() {
        if (!Validation.isValidEmail(Objects.requireNonNull(binding.txtEmail.getText()).toString())) {
            binding.emailLayout.setError("Please enter valid email");
        } else if (!Validation.isNotNullOrEmpty(binding.txtPass.getText().toString())) {
            binding.passwordLayout.setError("Please enter valid password");
            binding.emailLayout.setError(null);
        } else {
            binding.passwordLayout.setError(null);

            isAdmin = binding.txtEmail.getText().toString().trim().equals("admin");
            isKitchen = binding.txtEmail.getText().toString().trim().equals("kitchen");

            presenter.callLogin(binding.txtEmail.getText().toString().trim(),
                    binding.txtPass.getText().toString().trim());
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
        showSnackBar(binding.getRoot(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(LoginActivity.this, message);
    }

    @Override
    public void showSuccess(LoginResponse response) {
        showToast(LoginActivity.this, response.getMessage());
        sessionManager.setLogin(true);

        if (response.getData().getRole().equals("User")) {
            sessionManager.setUsertype(SessionManager.UserRoles.USER);
            sessionManager.setUserName(binding.txtEmail.getText().toString());
            startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
        } else if (response.getData().getRole().equals("Kitchen")) {
            sessionManager.setUsertype(SessionManager.UserRoles.KITCHEN);
            startActivity(new Intent(LoginActivity.this, KitchenActivity.class));
        } else {
            sessionManager.setUsertype(SessionManager.UserRoles.ADMIN);
            startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class));
        }
        finishAffinity();
    }

    @Override
    public void showInputWarning() {

    }
}