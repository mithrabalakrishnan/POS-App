package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.R;
import com.example.pos_android.contracts.ForecastingContract;
import com.example.pos_android.data.model.sales_report.SalesReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityForecastingBinding;
import com.example.pos_android.presenter.ForecastingPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

public class ForecastingActivity extends BaseActivity implements ForecastingContract.View {
    private ActivityForecastingBinding binding;
    private ForecastingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForecastingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        presenter = new ForecastingPresenter(this, this);
        presenter.getForecastingReport();
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
        showToast(ForecastingActivity.this, message);
    }

    @Override
    public void onForecastingResponse(SalesReportResponse saveResponse) {

    }
}