package com.example.pos_android.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.pos_android.databinding.ActivitySalesReportBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Objects;

public class SalesReportActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    private ActivitySalesReportBinding binding;
    String[] filterData = {"Weekly", "Monthly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySalesReportBinding.inflate(getLayoutInflater());
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

//    private void reportGraphData() {
//        ArrayList<BarEntry>barEntries = new ArrayList<>();
////        for (int i=1;i<10;i++){
////            float val = (float)(i*10);
////            BarEntry barEntry = new BarEntry(i,val);
////            barEntries.add(barEntry);
////        }
//        barEntries.add(new BarEntry(1.F, 200.0F,"Mon"));
//        barEntries.add(new BarEntry(2.F, 50.0F,"Tue"));
//        barEntries.add(new BarEntry(3.F, 200.0F,"Wed"));
//        barEntries.add(new BarEntry(4.F, 600.0F,"Thu"));
//        barEntries.add(new BarEntry(5.F, 250.0F,"Fri"));
//        barEntries.add(new BarEntry(6.F, 1000.0F,"Sat"));
//        barEntries.add(new BarEntry(7.F, 300.0F,"Sun"));
//        BarDataSet barDataSet= new BarDataSet(barEntries,"Weakly Report");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
////        barDataSet.setDrawValues(false);
//        binding.barChart.setData(new BarData(barDataSet));
//        binding.barChart.animateY(5000);
//        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
//        binding.barChart.getBarData().setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
////                return entry.getData().toString();
//                Log.e("fasafafa",""+entry.getData());
//                return entry.getData().toString();
//            }
//        });
//
//        binding.barChart.getDescription().setText("");
//        binding.barChart.getDescription().setTextColor(Color.BLUE);
//
//
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        //Toast.makeText(getApplicationContext(), filterData[position], Toast.LENGTH_LONG).show();
        if (Objects.equals(filterData[position], "Monthly")) {
            monthlyData();
        } else {
            weaklyData();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void weaklyData() {

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1.F, 200.0F, "Mon"));
        barEntries.add(new BarEntry(2.F, 50.0F, "Tue"));
        barEntries.add(new BarEntry(3.F, 200.0F, "Wed"));
        barEntries.add(new BarEntry(4.F, 600.0F, "Thu"));
        barEntries.add(new BarEntry(5.F, 250.0F, "Fri"));
        barEntries.add(new BarEntry(6.F, 1000.0F, "Sat"));
        barEntries.add(new BarEntry(7.F, 300.0F, "Sun"));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12);
        binding.barChart.setData(new BarData(barDataSet));
        binding.barChart.animateY(5000);
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());

        final String[] labels = new String[]{"start", "Mon", "Tue", "Wed", "Thu", "Fri",
                "Sat", "Sun"};
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.barChart.getXAxis().setGranularity(0f);
        binding.barChart.getXAxis().setGranularityEnabled(true);

        binding.txtPrice.setText("500 £");
        binding.txtPeople.setText("450");
        binding.barChart.getDescription().setText("");
        binding.barChart.getDescription().setTextColor(Color.BLUE);

    }

    public void monthlyData() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0.F, 200.0F, "Jan"));
        barEntries.add(new BarEntry(1.F, 50.0F, "Feb"));
        barEntries.add(new BarEntry(2.F, 200.0F, "Mar"));
        barEntries.add(new BarEntry(3.F, 600.0F, "Apr"));
        barEntries.add(new BarEntry(4.F, 250.0F, "May"));
        barEntries.add(new BarEntry(5.F, 1000.0F, "Jun"));
        barEntries.add(new BarEntry(6.F, 300.0F, "July"));
        barEntries.add(new BarEntry(7.F, 50.0F, "Aug"));
        barEntries.add(new BarEntry(8.F, 600.0F, "Sep"));
        barEntries.add(new BarEntry(9.F, 550.0F, "Oct"));
        barEntries.add(new BarEntry(10.F, 150.0F, "Nov"));
        barEntries.add(new BarEntry(11.F, 400.0F, "Dec"));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Report");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12);
        binding.barChart.setData(new BarData(barDataSet));
        binding.barChart.animateY(5000);
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter());
        binding.txtPrice.setText("20500 €");
        binding.txtPeople.setText("1500");
        binding.barChart.getDescription().setText("");
        binding.barChart.getDescription().setTextColor(Color.BLUE);
        binding.barChart.setDrawGridBackground(false);


        final String[] labels = new String[]{ "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        binding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        binding.barChart.getXAxis().setGranularity(1f);
        binding.barChart.getXAxis().setGranularityEnabled(true);
    }
}