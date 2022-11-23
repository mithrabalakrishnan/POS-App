package com.example.pos_android.view.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_android.adapter.BestSellingAdapter;
import com.example.pos_android.databinding.ActivityBestSellingReportBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Objects;

public class BestSellingReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] filterData = {"Weekly", "Monthly"};
    String[] titleList = {"Pizza", "Burger", "Bangers and Mash", "Sunday Roast", "Sandwich"};
    Double[] salesList = {25.0, 15.0, 11.0, 5.0, 17.0};
    BestSellingAdapter adapter;
    private ActivityBestSellingReportBinding binding;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (Objects.equals(filterData[position], "Monthly")) {
            monthlyData();
        } else {
            weeklyData();
        }
    }

    private void weeklyData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(200.0F, "Pizza"));
        pieEntries.add(new PieEntry(50.0F, "Burger"));
        pieEntries.add(new PieEntry(200.0F, "Bangers and Mash"));
        pieEntries.add(new PieEntry(25.0F, "Sunday Roast"));
        pieEntries.add(new PieEntry(150.0F, "Sandwich"));
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

    private void monthlyData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(200.0F, "Pizza"));
        pieEntries.add(new PieEntry(50.0F, "Burger"));
        pieEntries.add(new PieEntry(200.0F, "Bangers and Mash"));
        pieEntries.add(new PieEntry(300.0F, "Sunday Roast"));
        pieEntries.add(new PieEntry(150.0F, "Sandwich"));
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
        adapter = new BestSellingAdapter(this, this, titleList, salesList);
        binding.list.setAdapter(adapter);
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

}