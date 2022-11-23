package com.example.pos_android.view.admin;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_android.databinding.ActivityIncomeReportBinding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Objects;

public class IncomeReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ActivityIncomeReportBinding binding;
    String[] filterData = {"Pizza", "Pasta", "Koshari"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        reportGraphData();
        filterSpinnerSet();
        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
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
        if (Objects.equals(filterData[position], "Pizza")) {
            pizzaData();
        } else if(Objects.equals(filterData[position], "Pasta")) {
           sundayData();
        }else{
            koshariData();
        }
    }

    private void koshariData() {
        ArrayList<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(1.F, 110.0F, "Mon"));
        lineEntries.add(new Entry(2.F, 500.0F, "Tue"));
        lineEntries.add(new Entry(3.F, 200.0F, "Wed"));
        lineEntries.add(new Entry(4.F, 320.0F, "Thu"));
        lineEntries.add(new Entry(5.F, 150.0F, "Fri"));
        lineEntries.add(new Entry(6.F, 770.0F, "Sat"));
        lineEntries.add(new Entry(7.F, 300.0F, "Sun"));
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

    public void pizzaData(){
      ArrayList<Entry> lineEntries = new ArrayList<>();
      lineEntries.add(new Entry(1.F, 200.0F, "Mon"));
      lineEntries.add(new Entry(2.F, 50.0F, "Tue"));
      lineEntries.add(new Entry(3.F, 200.0F, "Wed"));
      lineEntries.add(new Entry(4.F, 600.0F, "Thu"));
      lineEntries.add(new Entry(5.F, 250.0F, "Fri"));
      lineEntries.add(new Entry(6.F, 800.0F, "Sat"));
      lineEntries.add(new Entry(7.F, 300.0F, "Sun"));
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
  public void sundayData(){
      ArrayList<Entry> lineEntries = new ArrayList<>();
      lineEntries.add(new Entry(1.F, 110.0F, "Mon"));
      lineEntries.add(new Entry(2.F, 75.0F, "Tue"));
      lineEntries.add(new Entry(3.F, 100.0F, "Wed"));
      lineEntries.add(new Entry(4.F, 300.0F, "Thu"));
      lineEntries.add(new Entry(5.F, 260.0F, "Fri"));
      lineEntries.add(new Entry(6.F, 600.0F, "Sat"));
      lineEntries.add(new Entry(7.F, 500.0F, "Sun"));
      LineDataSet lineDataSet = new LineDataSet(lineEntries, "Report");
      lineDataSet.setValueTextSize(13);
      lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
      binding.lineChart.setData(new LineData(lineDataSet));
      binding.lineChart.animateY(5000);
      binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

      final String[] labels = new String[]{"start", "Mon", "Tue", "Wed", "Thu", "Fri",
              "Sat", "Sun"};
      binding.lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
      binding.lineChart.getXAxis().setGranularity(0f);
      binding.lineChart.getXAxis().setGranularityEnabled(true);
  }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
