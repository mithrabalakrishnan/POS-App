package com.example.pos_android.view.kitchen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pos_android.R;
import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.KitchenUpdateStatusResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityKitchenOrderDetailBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

public class KitchenOrderDetailActivity extends BaseActivity implements KitchenOrderDetailContract.View {
    ActivityKitchenOrderDetailBinding binding;
    KitchenResponse.KitchenData kitchenData;
    KitchenPresenter presenter;
    int SMS_PERMISSION_CODE = 300;
    boolean hasPermission = false;
    String selectedStatus;
    private SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        kitchenData = (KitchenResponse.KitchenData) getIntent().getSerializableExtra("data");

        presenter = new KitchenPresenter(this, this);
        smsManager = SmsManager.getDefault();
        checkPermissions();
        binding.tvOrderId.setText("Order ID : #" + kitchenData.getId());
        binding.tvDate.setText(kitchenData.getDate());
        binding.tvTime.setText(kitchenData.getTime());
        binding.tvTableId.setText(kitchenData.getTableId().toString());
        binding.tvCustomerDetails.setText(String.format("%s\n%s", kitchenData.getUsername(), kitchenData.getUserPhoneNumber()));
        binding.txtFoodItems.setText(String.format("%s", kitchenData.getFoodName()));
        binding.txtFoodQty.setText(String.format("x %s", kitchenData.getQuanty()));
        Log.e("Status", String.valueOf(kitchenData.getStatus()));
        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        if (kitchenData.getStatus().equals("In-Progress")) {
            binding.rbInProgress.setChecked(true);
        } else if (kitchenData.getStatus().equals("Completed")) {

            binding.rbCompleted.setChecked(true);
            binding.rbInProgress.setEnabled(false);
            binding.rgStatus.setEnabled(false);
            binding.buttonStatus.setVisibility(View.GONE);
        } else {
            binding.rbCompleted.setChecked(false);
            binding.rbInProgress.setChecked(false);
        }

        //        if (kitchenData.getStatus() =="")
//        binding.tvCustomerDetails.setText(kitchenData.);
//        for (int i = 0; i < 5; i++) {
//            ///create linear layout programmatically
//            LinearLayout layout = new LinearLayout(this);
//            layout.setOrientation(LinearLayout.HORIZONTAL);
//            layout.setPadding(0, 8, 0, 0);
//
//            ///Adds the itemName
//            TextView itemName = new TextView(this);
//            itemName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//            itemName.setText("RiceAndChicken");
//            itemName.setTextSize(15);
//            itemName.setPadding(15, 0, 0, 0);
//            itemName.setTextColor(getResources().getColor(R.color.black));
//
//            ///Adds the itemCount
//            TextView itemCount = new TextView(this);
//            itemCount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//            itemCount.setText("X2");
//            itemCount.setTextSize(15);
//            itemCount.setGravity(Gravity.END);
//            itemCount.setPadding(0, 0, 15, 0);
//            itemCount.setTextColor(getResources().getColor(R.color.red_500));
//
//            layout.addView(itemName);
//            layout.addView(itemCount);
//
//            binding.layoutFoodDetails.addView(layout);
//        }

        binding.rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                binding.buttonStatus.setVisibility(View.VISIBLE);
                RadioButton button = findViewById(checkedId);
                selectedStatus = button.getText().toString();
            }
        });

        binding.buttonStatus.setOnClickListener(v -> {
            if (hasPermission) {
                presenter.updateKitchenOrder(kitchenData, selectedStatus);
            } else
                checkPermissions();
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
        if (string.equalsIgnoreCase(getResources().getString(R.string.unauthorized))) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.clear();
            showToast(this, "Session expired");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        } else
            showSnackBar(binding.getRoot(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        if (message != null) {
            showToast(KitchenOrderDetailActivity.this, message);
        }
    }

    @Override
    public void showSuccessResponse(KitchenUpdateStatusResponse saveResponse) {
        String message = "Hi Customer, Your order with id " + kitchenData.getId() + " Is " + saveResponse.getData().getStatus() + ". \nThanks from " + getResources().getString(R.string.app_name);
        Log.d("Update Status", message);
        try {
            smsManager.sendTextMessage(kitchenData.getUserPhoneNumber(), null, message, null, null);
            showWarningMessage(saveResponse.getMessage());
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(KitchenOrderDetailActivity.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            hasPermission = true;
        } else {
            ActivityCompat.requestPermissions(KitchenOrderDetailActivity.this, new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Checking whether user granted the permission or not.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Showing the toast message
            // showToast(AddFoodActivity.this, "Permission granted");
            hasPermission = true;
        } else {
            hasPermission = false;
            //  showToast(AddFoodActivity.this, "Permission denied");
        }
    }

}