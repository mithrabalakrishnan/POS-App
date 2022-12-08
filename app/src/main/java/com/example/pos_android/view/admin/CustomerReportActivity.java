package com.example.pos_android.view.admin;

import android.graphics.Color;
import android.os.Bundle;

import com.example.pos_android.databinding.ActivityCustomerReportBinding;
import com.example.pos_android.view.BaseActivity;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class CustomerReportActivity extends BaseActivity {
    private ActivityCustomerReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.iconBack.setOnClickListener(view -> {
            onBackPressed();
        });
        ArrayList<Double> dataList = new ArrayList<Double>();
        dataList.add(8.0);
        dataList.add(6.0);
        dataList.add(2.0);
        dataList.add(1.0);
        dataList.add(1.0);
        monthlyData(dataList);

    }

    public void monthlyData(ArrayList<Double> chart_data) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0.F, chart_data.get(0).floatValue()));
        barEntries.add(new BarEntry(1.F, chart_data.get(1).floatValue()));
        barEntries.add(new BarEntry(2.F, chart_data.get(2).floatValue()));
        barEntries.add(new BarEntry(3.F, chart_data.get(3).floatValue()));
        barEntries.add(new BarEntry(4.F, chart_data.get(4).floatValue()));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Customer Report");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(12);
        binding.chart.setData(new BarData(barDataSet));
        binding.chart.animateY(5000);
        binding.chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        binding.chart.getDescription().setText("");
        binding.chart.getDescription().setTextColor(Color.BLUE);
        binding.chart.setDrawGridBackground(false);


        final String[] labels = new String[]{"Alen", "George", "May", "John", "Amith"};
        binding.chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.chart.getXAxis().setGranularity(1f);
        binding.chart.getXAxis().setGranularityEnabled(true);
    }
}