package com.example.pos_android.view.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pos_android.databinding.ActivityKitchenBinding;

public class KitchenActivity extends AppCompatActivity {
   ActivityKitchenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}