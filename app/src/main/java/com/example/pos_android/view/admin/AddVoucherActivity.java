package com.example.pos_android.view.admin;

import android.os.Bundle;

import com.example.pos_android.contracts.VoucherContract;
import com.example.pos_android.data.model.GetVoucherResponse;
import com.example.pos_android.databinding.ActivityAddVoucherBinding;
import com.example.pos_android.presenter.VoucherPresenter;
import com.example.pos_android.utils.Validation;
import com.example.pos_android.view.BaseActivity;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddVoucherActivity extends BaseActivity implements VoucherContract.View {
    private ActivityAddVoucherBinding binding;
    private VoucherPresenter presenter;
    private String expDate = null;
    private MaterialDatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {

        CalendarConstraints calendarConstraints = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
                .build();

        datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setCalendarConstraints(calendarConstraints)
                .setTitleText("Select expiry date")
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            expDate = format.format(selection);
            binding.tvDate.setText(expDate);
        });

        presenter = new VoucherPresenter(this, this);
        binding.iconBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnAdd.setOnClickListener(v -> {
            validateFields();
        });

        binding.btnPick.setOnClickListener(v -> {
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });
    }

    private void validateFields() {
        if (!Validation.isNotNullOrEmpty(binding.txtName.getText().toString())) {
            binding.textLayoutName.setError("Please enter title");
        } else if (!Validation.isNotNullOrEmpty(binding.txtCategory.getText().toString())) {
            binding.txtLayoutCategory.setError("Please enter voucher code");
            binding.textLayoutName.setError(null);
        } else if (!Validation.isNotNullOrEmpty(binding.txtPrice.getText().toString())) {
            binding.txtPrice.setError("Please enter discount");
            binding.txtLayoutCategory.setError(null);
        } else if (!Validation.isNotNullOrEmpty(expDate)) {
            showSnackBar(binding.getRoot(), "Please choose expiry date");
            binding.txtLayoutCategory.setError(null);
        } else {
            presenter.callAddVoucher(binding.txtName.getText().toString(),
                    binding.txtCategory.getText().toString(), binding.txtPrice.getText().toString(),
                    expDate
            );
        }
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
        hideLoadingDialog();
        showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(this, message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void showAddVoucherApiResponseSuccess(String response) {
        hideLoadingDialog();
        showToast(this, response);
        finish();
    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void showAllVouchers(GetVoucherResponse response) {

    }
}