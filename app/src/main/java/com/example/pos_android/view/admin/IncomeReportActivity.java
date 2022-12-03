package com.example.pos_android.view.admin;


import android.os.Bundle;
import android.view.View;

import com.example.pos_android.contracts.IncomePerItemMonthlyContract;
import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;
import com.example.pos_android.databinding.ActivityIncomeReportBinding;
import com.example.pos_android.presenter.IncomePerItemMonthlyPresenter;
import com.example.pos_android.view.BaseActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class IncomeReportActivity extends BaseActivity implements IncomePerItemMonthlyContract.View {
    private ActivityIncomeReportBinding binding;
    private IncomePerItemMonthlyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        presenter = new IncomePerItemMonthlyPresenter(this, this);
        int foodId = getIntent().getExtras().getInt("id");
        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        presenter.geIncomePerItemMonthly(foodId);
    }

    public void FoodData(List<Double> chart_data) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1.F, chart_data.get(0).floatValue(), "Mon"));
        lineEntries.add(new Entry(2.F, chart_data.get(1).floatValue(), "Tue"));
        lineEntries.add(new Entry(3.F, chart_data.get(2).floatValue(), "Wed"));
        lineEntries.add(new Entry(4.F, chart_data.get(3).floatValue(), "Thu"));
        lineEntries.add(new Entry(5.F, chart_data.get(4).floatValue(), "Fri"));
        lineEntries.add(new Entry(6.F, chart_data.get(5).floatValue(), "Sat"));
        lineEntries.add(new Entry(7.F, chart_data.get(6).floatValue(), "Sun"));
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Report");
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.lineChart.setData(new LineData(lineDataSet));
        binding.lineChart.animateY(3000);
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        final String[] labels = new String[]{"start", "Mon", "Tue", "Wed", "Thu", "Fri",
                "Sat", "Sun"};
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.lineChart.getXAxis().setGranularity(0f);
        binding.lineChart.getXAxis().setGranularityEnabled(true);


    }

    public void foodDataMonthly(List<Double> chart_data) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < chart_data.size(); i++) {
            lineEntries.add(new Entry(i + 1F, chart_data.get(i).floatValue(), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Report");
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.lineChart.setData(new LineData(lineDataSet));
        binding.lineChart.animateY(3000);
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        final String[] labels = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri",
                "Sat", "Sun"};
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.lineChart.getXAxis().setGranularity(0f);
        binding.lineChart.getXAxis().setGranularityEnabled(true);


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
        showToast(IncomeReportActivity.this, message);
    }


    @Override
    public void showIncomePerItemMonthlyResponse(IncomePerItemMonthlyResponse response) {
        foodDataMonthly(response.getData());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
