package com.example.pos_android.view.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.button.AlanButton;
import com.example.pos_android.R;
import com.example.pos_android.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHomeActivity extends AppCompatActivity {
    private static final String SDK_KEY = "63100bd6601eedb6185878a28d125e562e956eca572e1d8b807a3e2338fdd0dc/stage";
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {

        AlanButton alanButton = binding.alanButton;
        AlanConfig config = AlanConfig.builder()
                .setProjectId(SDK_KEY).build();
        alanButton.initWithConfig(config);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }
}