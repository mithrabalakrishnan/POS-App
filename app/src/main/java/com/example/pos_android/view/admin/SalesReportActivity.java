package com.example.pos_android.view.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.core.util.Pair;

import com.example.pos_android.R;
import com.example.pos_android.contracts.SalesReportContract;
import com.example.pos_android.data.model.sales_report.SalesReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivitySalesReportBinding;
import com.example.pos_android.presenter.SalesReportPresenter;
import com.example.pos_android.utils.RangeDateValidator;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SalesReportActivity extends BaseActivity implements
        AdapterView.OnItemSelectedListener, SalesReportContract.View {
    String[] filterData = {"Weekly", "Monthly"};
    MaterialDatePicker materialDatePicker;
    List<String> dayList = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();
    private String[] dayArray = {};
    private ActivitySalesReportBinding binding;
    private SalesReportPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySalesReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SalesReportPresenter(this, this);


        filterSpinnerSet();
        binding.iconBack.setOnClickListener(view -> onBackPressed());

        binding.btnFrom.setOnClickListener(v -> {
            MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder
                    = MaterialDatePicker.Builder.dateRangePicker();
            CalendarConstraints.Builder calendarConstraints = new CalendarConstraints.Builder();
            RangeDateValidator validator = new RangeDateValidator(7);
            calendarConstraints.setValidator(validator);
            materialDateBuilder.setCalendarConstraints(calendarConstraints.build());
            materialDateBuilder.setTitleText("Select week");
            materialDatePicker = materialDateBuilder.build();
            validator.setDatePicker(materialDatePicker);


            materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    dateList.clear();
                    Pair<Long, Long> dateLongs = (Pair<Long, Long>) selection;
                    binding.tvDateRange.setVisibility(View.VISIBLE);
                    binding.tvDateRange.setText("Selected Week : " + materialDatePicker.getHeaderText());
                    String startDate = android.text.format.DateFormat.format("yyyy-MM-dd", new Date(dateLongs.first)).toString();
                    String endDate = android.text.format.DateFormat.format("yyyy-MM-dd", new Date(dateLongs.second)).toString();
                    Format f = new SimpleDateFormat("EEE");
                    List<Date> dates = getDates(startDate, endDate);


                    dayList.clear();
                    for (Date date : dates) {
                        dateList.add(String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd", date)));
                        dayList.add(f.format(date));
                        Log.d("Day", f.format(date));

                    }

                    presenter.callWeaklySalesReport(dateList);
                }
            });
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
    }


    private void filterSpinnerSet() {
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //Toast.makeText(getApplicationContext(), filterData[position], Toast.LENGTH_LONG).show();
        binding.tvDateRange.setText("");
        binding.tvDateRange.setVisibility(View.GONE);
        if (Objects.equals(filterData[position], "Monthly")) {
//            monthlyData();
            binding.fromLayout.setVisibility(View.GONE);
            presenter.callSalesReport("monthly");
        } else {
            binding.fromLayout.setVisibility(View.VISIBLE);
//            weaklyData();
            //  presenter.callSalesReport("weekly");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void weaklyData(ArrayList<Double> chart_data) {

        ArrayList<Entry> barEntries = new ArrayList<>();
        for (int i = 0; i < chart_data.size(); i++) {
            barEntries.add(new Entry(i, chart_data.get(i).floatValue(), ""));
        }
        LineDataSet lineDataSet = new LineDataSet(barEntries, "Weekly Report");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextSize(12);
        binding.barChart.setData(new LineData(lineDataSet));
        binding.barChart.animateY(5000);
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        Arrays.fill(dayArray, null);


        final String[] labels = dayList.toArray(new String[0]);
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.barChart.getXAxis().setGranularity(0f);
        binding.barChart.getXAxis().setGranularityEnabled(true);

        binding.barChart.getDescription().setText("");
        binding.barChart.getDescription().setTextColor(Color.BLUE);

    }

    public void monthlyData(ArrayList<Double> chart_data) {
        ArrayList<Entry> barEntries = new ArrayList<>();
        barEntries.add(new Entry(0.F, chart_data.get(0).floatValue(), "Jan"));
        barEntries.add(new Entry(1.F, chart_data.get(1).floatValue(), "Feb"));
        barEntries.add(new Entry(2.F, chart_data.get(2).floatValue(), "Mar"));
        barEntries.add(new Entry(3.F, chart_data.get(3).floatValue(), "Apr"));
        barEntries.add(new Entry(4.F, chart_data.get(4).floatValue(), "May"));
        barEntries.add(new Entry(5.F, chart_data.get(5).floatValue(), "Jun"));
        barEntries.add(new Entry(6.F, chart_data.get(6).floatValue(), "July"));
        barEntries.add(new Entry(7.F, chart_data.get(7).floatValue(), "Aug"));
        barEntries.add(new Entry(8.F, chart_data.get(8).floatValue(), "Sep"));
        barEntries.add(new Entry(9.F, chart_data.get(9).floatValue(), "Oct"));
        barEntries.add(new Entry(10.F, chart_data.get(10).floatValue(), "Nov"));
        barEntries.add(new Entry(11.F, chart_data.get(11).floatValue(), "Dec"));
        LineDataSet barDataSet = new LineDataSet(barEntries, "Monthly Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12);
        binding.barChart.setData(new LineData(barDataSet));
        binding.barChart.animateY(5000);
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        binding.barChart.getDescription().setText("");
        binding.barChart.getDescription().setTextColor(Color.BLUE);
        binding.barChart.setDrawGridBackground(false);


        final String[] labels = new String[]{"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.barChart.getXAxis().setGranularity(1f);
        binding.barChart.getXAxis().setGranularityEnabled(true);
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
        showToast(SalesReportActivity.this, message);
    }

    @Override
    public void showSuccess(SalesReportResponse saveResponse) {
        binding.txtPrice.setText(String.valueOf(saveResponse.data.total_sale));
        binding.txtPeople.setText(String.valueOf(saveResponse.data.total_customers));
        if (saveResponse.data.type.equals("Weekly")) {
            weaklyData(saveResponse.data.chart_data);
        } else {
            monthlyData(saveResponse.data.chart_data);
        }
    }

    @Override
    public void onWeaklySalesReportResponse(SalesReportResponse saveResponse) {

    }

    @Override
    public void showInputWarning() {

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