package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_android.databinding.ActivityReportBinding;

public class ReportActivity extends AppCompatActivity {
    ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnClick();
    }

    private void btnClick() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.btnSalesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportActivity.this, SalesReportActivity.class));
            }
        });

        binding.btnBestSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportActivity.this, BestSellingReportActivity.class));
            }
        });

        binding.totalIncomeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportActivity.this,ListAllFoodActivity.class));
            }
        });

        binding.customerReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportActivity.this,CustomerReportActivity.class));
            }
        });

    }
}