package com.example.pos_android.view.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.core.util.Pair;

import com.example.pos_android.adapter.BestSellingAdapter;
import com.example.pos_android.contracts.BestSellingReportContract;
import com.example.pos_android.data.model.sales_report.BestSellingReportResponse;
import com.example.pos_android.databinding.ActivityBestSellingReportBinding;
import com.example.pos_android.presenter.BestSellingReportPresenter;
import com.example.pos_android.view.BaseActivity;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.Objects;

public class BestSellingReportActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,
        BestSellingReportContract.View {
    String[] filterData = {"Weekly", "Monthly"};
    String[] titleList = {"Pizza", "Burger", "Bangers and Mash", "Sunday Roast", "Sandwich"};
    Double[] salesList = {25.0, 15.0, 11.0, 5.0, 17.0};
    BestSellingAdapter adapter;
    private ActivityBestSellingReportBinding binding;
    MaterialDatePicker materialDatePicker;
    private BestSellingReportPresenter presenter;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        binding.tvDateRange.setText("");
        binding.tvDateRange.setVisibility(View.GONE);
        if (Objects.equals(filterData[position], "Monthly")) {
//            monthlyData();
//            binding.fromLayout.setVisibility(View.GONE);
            presenter.getBestSellingReport("monthly");
        } else {
//            binding.fromLayout.setVisibility(View.VISIBLE);
//            weeklyData();
          //  presenter.getBestSellingReport("weekly");
        }
    }

    private void weeklyData(ArrayList<Double> chart_data) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(chart_data.get(0).floatValue(), "Pizza"));
        pieEntries.add(new PieEntry(chart_data.get(1).floatValue(), "Burger"));
        pieEntries.add(new PieEntry(chart_data.get(2).floatValue(), "Bangers and Mash"));
        pieEntries.add(new PieEntry(chart_data.get(3).floatValue(), "Sunday Roast"));
        pieEntries.add(new PieEntry(chart_data.get(4).floatValue(), "Sandwich"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Weekly Report");
        pieDataSet.setValueTextSize(12);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        binding.pieChart.setData(new PieData(pieDataSet));
        binding.pieChart.setEntryLabelTextSize(8f);
        binding.pieChart.animateY(5000);
        // binding.pieChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        binding.pieChart.getDescription().setText("");
        binding.pieChart.getDescription().setTextColor(Color.BLUE);


        final String[] labels = new String[]{"start", "Mon", "Tue", "Wed", "Thu", "Fri",
                "Sat", "Sun"};
//        binding.pieChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        binding.pieChart.getXAxis().setGranularity(1f);
//        binding.pieChart.getXAxis().setGranularityEnabled(true);
    }

    private void monthlyData(ArrayList<Double> chart_data) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(chart_data.get(0).floatValue(), "Pizza"));
        pieEntries.add(new PieEntry(chart_data.get(1).floatValue(), "Burger"));
        pieEntries.add(new PieEntry(chart_data.get(2).floatValue(), "Bangers and Mash"));
        pieEntries.add(new PieEntry(chart_data.get(3).floatValue(), "Sunday Roast"));
        pieEntries.add(new PieEntry(chart_data.get(4).floatValue(), "Sandwich"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Monthly Report");
        binding.pieChart.setEntryLabelTextSize(8f);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(12);
        binding.pieChart.setData(new PieData(pieDataSet));
        binding.pieChart.animateY(5000);
        //  binding.pieChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        binding.pieChart.getDescription().setText("");
        binding.pieChart.getDescription().setTextColor(Color.BLUE);


        final String[] labels = new String[]{"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    /*    binding.pieChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.pieChart.getXAxis().setGranularity(1f);
        binding.pieChart.getXAxis().setGranularityEnabled(true);*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBestSellingReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new BestSellingReportPresenter(this,this);

        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder
                = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText("Select date range");
        materialDatePicker = materialDateBuilder.build();

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            binding.tvDateRange.setVisibility(View.VISIBLE);
            binding.tvDateRange.setText("Selected Date Range : " + materialDatePicker.getHeaderText());
        });
        filterSpinnerSet();
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnFrom.setOnClickListener(v -> {
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        filterSpinnerSet();

    }

    private void filterSpinnerSet() {
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        showSnackBar(binding.getRoot(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(BestSellingReportActivity.this, message);
    }


    @Override
    public void showBestSellingReportResponse(BestSellingReportResponse response) {
        adapter = new BestSellingAdapter(this, this, response.data.food_details);
        binding.list.setAdapter(adapter);
        if(response.data.type.equals("weekly")){
            weeklyData(response.data.chart_data);
        }
        else{
            monthlyData(response.data.chart_data);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}