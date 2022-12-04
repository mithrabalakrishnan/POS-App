package com.example.pos_android.view.kitchen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pos_android.R;
import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.databinding.ActivityKitchenOrderDetailBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.admin.AddFoodActivity;

import java.util.Objects;

public class KitchenOrderDetailActivity extends BaseActivity implements KitchenOrderDetailContract.View {
    ActivityKitchenOrderDetailBinding binding;
    KitchenResponse.KitchenData kitchenData;
    KitchenPresenter presenter;
    String selectedStatus;
    boolean hasPermission = false;
    int SMS_PERMISSION_CODE = 300;

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
        binding.tvOrderId.setText("Order ID : #00" + kitchenData.getId());
        binding.tvDate.setText(kitchenData.getDate());
        binding.tvTime.setText(kitchenData.getTime());
        binding.tvTableId.setText(kitchenData.getTableId());
//        if (kitchenData.getStatus() =="")
//        binding.tvCustomerDetails.setText(kitchenData.);
        for (int i = 0; i < 5; i++) {
            ///create linear layout programmatically
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setPadding(0, 8, 0, 0);

            ///Adds the itemName
            TextView itemName = new TextView(this);
            itemName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            itemName.setText("RiceAndChicken");
            itemName.setTextSize(15);
            itemName.setPadding(15, 0, 0, 0);
            itemName.setTextColor(getResources().getColor(R.color.black));

            ///Adds the itemCount
            TextView itemCount = new TextView(this);
            itemCount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            itemCount.setText("X2");
            itemCount.setTextSize(15);
            itemCount.setGravity(Gravity.END);
            itemCount.setPadding(0, 0, 15, 0);
            itemCount.setTextColor(getResources().getColor(R.color.red_500));

            layout.addView(itemName);
            layout.addView(itemCount);

            binding.layoutFoodDetails.addView(layout);
        }

        binding.rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                binding.buttonStatus.setVisibility(View.VISIBLE);
                RadioButton button = findViewById(checkedId);
                selectedStatus = button.getText().toString();
            }
        });

        binding.buttonStatus.setOnClickListener(v -> {
//              presenter.updateKitchenOrder(kitchenData, selectedStatus);
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
        showToast(KitchenOrderDetailActivity.this, message);
    }

    @Override
    public void showSuccessResponse(CommonResponse saveResponse) {
        showWarningMessage(saveResponse.getMessage());
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(KitchenOrderDetailActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            hasPermission = true;
        } else {
            ActivityCompat.requestPermissions(KitchenOrderDetailActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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