package com.example.pos_android.view.admin;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.core.util.Pair;

import com.example.pos_android.R;
import com.example.pos_android.contracts.IncomePerItemMonthlyContract;
import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityIncomeReportBinding;
import com.example.pos_android.presenter.IncomePerItemMonthlyPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class IncomeReportActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, IncomePerItemMonthlyContract.View {
    MaterialDatePicker materialDatePicker;
    String[] filterData = {"Weekly", "Monthly"};
    int foodId = 0;
    private ActivityIncomeReportBinding binding;
    private IncomePerItemMonthlyPresenter presenter;
    private List<String> dateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder
                = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("Select date range");
        materialDatePicker = materialDateBuilder.build();

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            dateList.clear();
            Pair<Long, Long> dateLongs = (Pair<Long, Long>) selection;
            binding.tvDateRange.setVisibility(View.VISIBLE);
            binding.tvDateRange.setText("Selected Week : " + materialDatePicker.getHeaderText());
            String startDate = android.text.format.DateFormat.format("yyyy-MM-dd", new Date(dateLongs.first)).toString();
            String endDate = android.text.format.DateFormat.format("yyyy-MM-dd", new Date(dateLongs.second)).toString();
            List<Date> dates = getDates(startDate, endDate);

            for (Date date : dates) {
                dateList.add(String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", date)));
                Log.d("Date List", String.valueOf(android.text.format.DateFormat.format("dd/MM/yyyy", date)));
            }

            presenter.geIncomePerItemWeakly(foodId, dateList);
        });
    }

    private void initView() {
        presenter = new IncomePerItemMonthlyPresenter(this, this);
        foodId = getIntent().getExtras().getInt("id");
        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);

        binding.btnFrom.setOnClickListener(view -> {
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
    }

    public void foodDataMonthly(List<Double> chart_data) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < chart_data.size(); i++) {
            lineEntries.add(new Entry(i, chart_data.get(i).floatValue(), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Monthly Report");
        lineDataSet.setValueTextSize(12);
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.lineChart.setData(new LineData(lineDataSet));
        binding.lineChart.animateY(3000);
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        final String[] labels = new String[]{"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.lineChart.getXAxis().setGranularity(0f);
        binding.lineChart.getXAxis().setGranularityEnabled(true);

    }

    public void foodDataWeekly(List<Double> chart_data) {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < chart_data.size(); i++) {
            lineEntries.add(new Entry(i, chart_data.get(i).floatValue(), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Weekly Report");
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
        showToast(IncomeReportActivity.this, message);
    }


    @Override
    public void showIncomePerItemMonthlyResponse(IncomePerItemMonthlyResponse response) {
        foodDataMonthly(response.getData());
    }

    @Override
    public void showIncomePerItemWeeklyResponse(IncomePerItemMonthlyResponse response) {
        foodDataWeekly(response.getData());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        binding.tvDateRange.setText("");
        binding.tvDateRange.setVisibility(View.GONE);
        if (Objects.equals(filterData[position], "Monthly")) {
//            monthlyData();
            binding.fromLayout.setVisibility(View.GONE);
            presenter.geIncomePerItemMonthly(foodId);
        } else {
            binding.fromLayout.setVisibility(View.VISIBLE);
//            weeklyData();
            //  presenter.getBestSellingWeaklyReport("weekly");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private List<Date> getDates(String startDate, String endDate) {
        List<Date> dates = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = dateFormat.parse(startDate);
            end = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        assert start != null;
        calendar1.setTime(start);
        Calendar calendar2 = Calendar.getInstance();
        assert end != null;
        calendar2.setTime(end);
        while (!calendar1.after(calendar2)) {
            dates.add(calendar1.getTime());
            calendar1.add(Calendar.DATE, 1);
        }
        return dates;
    }
}
