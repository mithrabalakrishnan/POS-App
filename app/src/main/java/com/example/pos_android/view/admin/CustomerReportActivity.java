package com.example.pos_android.view.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.core.content.ContextCompat;

import com.example.pos_android.R;
import com.example.pos_android.contracts.CustomerReportContract;
import com.example.pos_android.data.model.CustomerReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityCustomerReportBinding;
import com.example.pos_android.presenter.CustomerReportPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomerReportActivity extends BaseActivity implements CustomerReportContract.View, AdapterView.OnItemSelectedListener {
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    String[] filterData = {"Select", "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER",
            "NOVEMBER", "DECEMBER"};
    private ActivityCustomerReportBinding binding;
    private CustomerReportPresenter reportPresenter;
    private String[] labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        reportPresenter = new CustomerReportPresenter(this, this);
        binding.iconBack.setOnClickListener(view -> {
            onBackPressed();
        });
        filterSpinnerSet();
    }

    private void filterSpinnerSet() {
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);
    }


    public void monthlyData() {
        BarDataSet barDataSet = new BarDataSet(barEntries, "Customer Report");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(12);
        binding.chart.setData(new BarData(barDataSet));
        binding.chart.animateY(5000);
        binding.chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        binding.chart.getDescription().setText("");
        binding.chart.getDescription().setTextColor(Color.BLUE);
        binding.chart.setDrawGridBackground(false);
        binding.chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.chart.getXAxis().setGranularity(1f);
        binding.chart.getXAxis().setGranularityEnabled(true);
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
    public void showReportResponse(CustomerReportResponse response) {
        barEntries.clear();
        ArrayList<CustomerReportResponse.UserReport> reportArrayList = new ArrayList<>(response.getData().getUserReport());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(reportArrayList, Comparator.comparingInt(CustomerReportResponse.UserReport::getVisitList).reversed());
        }

        labels = new String[response.getData().getUserReport().size()];
        int totalUsers = 0;

        for (int i = 0; i < response.getData().getUserReport().size(); i++) {
            if(reportArrayList.get(i).getVisitList() != 0) {
                barEntries.add(new BarEntry(i, (float) reportArrayList.get(i).getVisitList()));
                labels[i] = reportArrayList.get(i).getUsername();
                totalUsers = totalUsers + 1;
            }
        }
        binding.txtPeople.setText(String.valueOf(totalUsers));
        monthlyData();
        if(totalUsers == 0){
           binding.chart.clear();
           binding.chart.setNoDataText("No data available in selected date");
           binding.chart.setNoDataTextColor(ContextCompat.getColor(this, R.color.red_500));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = filterData[position];
        if (!selectedItem.equals("Select")) {
            binding.tvDateRange.setText(filterData[position]);
            reportPresenter.getReport(filterData[position]);
        }else{
            binding.chart.clear();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}