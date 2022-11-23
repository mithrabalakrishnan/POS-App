package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pos_android.R;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityAdminHomeBinding;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AdminHomeActivity extends BaseActivity {

    private ActivityAdminHomeBinding binding;
    private SessionManager sessionManager;
//     List<SlideModel> slideModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sessionManager = new SessionManager(this);

        initUi();
        btnClick();


    }

    private void initUi() {

    }

    private void btnClick() {
        binding.addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddFoodActivity.class));
            }
        });

        binding.btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
        binding.btnReport.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ReportActivity.class));
        });
    }

    private void showLogoutDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Logout");
        builder.setIcon(R.drawable.app_main);
        builder.setMessage("Are you sure want to Logout ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            sessionManager.clear();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
            dialogInterface.dismiss();
        }).setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss()).show();

    }
}