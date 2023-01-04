package com.example.pos_android.view.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.pos_android.R;
import com.example.pos_android.contracts.ForecastingContract;
import com.example.pos_android.data.model.sales_report.forecasting.dataPrediction;
import com.example.pos_android.data.model.sales_report.forecasting.dataRevenue;
import com.example.pos_android.data.model.sales_report.forecasting.forcasting_response;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityForecastingBinding;
import com.example.pos_android.presenter.ForecastingPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ForecastingActivity extends BaseActivity implements ForecastingContract.View {
    //    private ActivityForecastingBinding binding;
    private ActivityForecastingBinding binding;
    private ForecastingPresenter presenter;
    private String[] key = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};

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

        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
//        predictionGraph(saveResponse.getData().getPrediction());
//        revenueGraph(saveResponse.getData().getPrediction(),saveResponse.getData().getRevenue());

        binding.revenueReport.getDescription().setEnabled(false);
        binding.revenueReport.setBackgroundColor(Color.WHITE);
        binding.revenueReport.setDrawGridBackground(false);
        binding.revenueReport.setDrawBarShadow(false);
        binding.revenueReport.setHighlightFullBarEnabled(false);

        binding.revenueReport.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        Legend l = binding.revenueReport.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = binding.revenueReport.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setGranularity(10f);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = binding.revenueReport.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        rightAxis.setGranularity(1f);
        rightAxis.setTextSize(8f);
        leftAxis.setTextSize(8f);
        leftAxis.setAxisMinimum(0f);

        XAxis xAxis = binding.revenueReport.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setTextSize(8f);
        xAxis.setGranularity(1f);
        final String[] labels = new String[]{"0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9"};
        binding.revenueReport.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));


        CombinedData data = new CombinedData();

        data.setData(generateLinePredictionData(saveResponse.getData().getPrediction()));
        data.setData(generateBarRevenueData(saveResponse.getData().getRevenue()));

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        binding.revenueReport.setData(data);
        binding.revenueReport.invalidate();
    }


    private LineData generateLinePredictionData(ArrayList<dataPrediction> prediction) {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 10; i++)
            entries.add(new Entry(i, Float.parseFloat(prediction.get(i).getValue()), ""));

        LineDataSet set = new LineDataSet(entries, "Prediction Data");
        set.setColor(Color.rgb(0, 0, 100));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(0, 0, 100));
        set.setCircleRadius(5f);
        set.setFillColor(Color.rgb(0, 0, 100));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(8f);
        set.setValueTextColor(Color.rgb(0, 0, 100));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarRevenueData(ArrayList<dataRevenue> revenue) {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 10; i++) {
            entries.add(new BarEntry(i, Float.parseFloat(revenue.get(i).getValue()), ""));
        }

        BarDataSet set = new BarDataSet(entries, "Revenue Data");
        set.setColor(Color.rgb(60, 220, 78));
        set.setValueTextColor(Color.rgb(100, 0, 0));
        set.setValueTextSize(8f);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);

        return new BarData(set);
    }
}