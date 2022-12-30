package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;

import com.example.pos_android.R;
import com.example.pos_android.contracts.ForecastingContract;
import com.example.pos_android.data.model.sales_report.SalesReportResponse;
import com.example.pos_android.data.model.sales_report.forecasting.dataPrediction;
import com.example.pos_android.data.model.sales_report.forecasting.dataRevenue;
import com.example.pos_android.data.model.sales_report.forecasting.forcasting_response;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityForecastingBinding;
import com.example.pos_android.presenter.ForecastingPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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
    public void onForecastingResponse(forcasting_response saveResponse) {
        predictionGraph(saveResponse.getData().getPrediction());
        revenueGraph(saveResponse.getData().getRevenue());
    }

    public void predictionGraph(ArrayList<dataPrediction> prediction) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lineEntries.add(new Entry(i, Float.parseFloat(prediction.get(i).getValue()), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Prediction Report");
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.foreCastingReport.setData(new LineData(lineDataSet));
        binding.foreCastingReport.animateY(3000);
        binding.foreCastingReport.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        final String[] labels = new String[]{"0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19"};
        binding.foreCastingReport.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.foreCastingReport.getXAxis().setGranularity(0f);
        binding.foreCastingReport.getXAxis().setGranularityEnabled(true);

    }

    public void revenueGraph(ArrayList<dataRevenue> revenue) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lineEntries.add(new Entry(i, Float.parseFloat(revenue.get(i).getValue()), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Revenue Report");
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.revenueReport.setData(new LineData(lineDataSet));
        binding.revenueReport.animateY(3000);
        binding.revenueReport.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        final String[] labels = new String[]{"0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19"};
        binding.revenueReport.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.revenueReport.getXAxis().setGranularity(0f);
        binding.revenueReport.getXAxis().setGranularityEnabled(true);

    }
}