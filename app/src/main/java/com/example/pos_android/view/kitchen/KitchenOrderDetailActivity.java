package com.example.pos_android.view.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pos_android.databinding.ActivityKitchenOrderDetailBinding;

public class KitchenOrderDetailActivity extends AppCompatActivity {
  ActivityKitchenOrderDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}