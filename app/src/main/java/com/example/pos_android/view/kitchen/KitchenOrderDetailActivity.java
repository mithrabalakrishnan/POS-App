package com.example.pos_android.view.kitchen;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.databinding.ActivityKitchenOrderDetailBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.view.BaseActivity;

import java.util.Objects;

public class KitchenOrderDetailActivity extends BaseActivity implements KitchenOrderDetailContract.View, AdapterView.OnItemSelectedListener {
    ActivityKitchenOrderDetailBinding binding;
    String[] filterData = {"Select", "In-Progress", "Completed"};
    KitchenResponse.KitchenData kitchenData;
    KitchenPresenter presenter;
    String selectedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, filterData);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);

        kitchenData = (KitchenResponse.KitchenData) getIntent().getSerializableExtra("data");
        binding.tvStatus.setText(kitchenData.getStatus() != null ? "Current Status :" + kitchenData.getStatus() : "Current Status : TO-DO");
        binding.tvDetails.setText(
                "Food Id :" + kitchenData.getFoodId()   + "\n" +
                        "Quantity :" + String.valueOf(kitchenData.getQuanty())   + "\n" +
                        "User Id :" + String.valueOf(kitchenData.getUserId())   + "\n" +
                        "Time :" + kitchenData.getTime()   + "\n" +
                        "Date :" + kitchenData.getDate()   + "\n" +
                        "Table Id :" + String.valueOf(kitchenData.getTableId())
        );
        presenter = new KitchenPresenter(this, this);

        binding.buttonStatus.setOnClickListener(v -> {
            if (!Objects.equals(selectedStatus, "Select")) {
                presenter.updateKitchenOrder(kitchenData, selectedStatus);
            } else
                showSnackBar(binding.getRoot(), "Please select status");
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedStatus = filterData[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}