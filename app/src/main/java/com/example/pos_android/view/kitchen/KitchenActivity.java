package com.example.pos_android.view.kitchen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pos_android.R;
import com.example.pos_android.adapter.KitchenOrderListingAdapter;
import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityKitchenBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KitchenActivity extends BaseActivity implements KitchenListingContract.View, OnItemClickListener {
    ActivityKitchenBinding binding;
    private KitchenPresenter presenter;
    private KitchenOrderListingAdapter adapter;
    private List<KitchenResponse.KitchenData> kitchenDataList = new ArrayList<>();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKitchenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        presenter = new KitchenPresenter(this, this);
        sessionManager = new SessionManager(this);
        adapter = new KitchenOrderListingAdapter(kitchenDataList, this);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rvOrder.setAdapter(adapter);
        binding.btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.callKitchenOrderList();
        presenter.getUserProfile();
    }

    @Override
    public void onItemClick(Integer position, String from) {
        KitchenResponse.KitchenData data = kitchenDataList.get(position);
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (dateFormat.format(cal.getTime()).equals(data.getDate())) {
            Intent i = new Intent(this, KitchenOrderDetailActivity.class);
            i.putExtra("data", data);
            startActivity(i);
        } else
            showSnackBar(binding.getRoot(), "Can't update order on before or after today");
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
            showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(KitchenActivity.this, message);
    }

    @Override
    public void showUserProfileResponse(UserProfileResponse response) {
        binding.tvUser.setText("Hi, " + response.getData().username);
    }

    @Override
    public void showKitchenOrderListApiSuccess(List<KitchenResponse.KitchenData> saveResponse) {
        if (saveResponse.size() > 0) {
            kitchenDataList.clear();
            try {
                Calendar cal = Calendar.getInstance();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("Today's date is " + dateFormat.format(cal.getTime()));
                for (KitchenResponse.KitchenData data : saveResponse) {
                    if (dateFormat.parse(data.getDate()).after(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))) {
                        kitchenDataList.add(data);
                    }
                    adapter.notifyDataSetChanged();
                }
            } catch (ParseException e) {
                Log.d("Date exception", "showAllVouchers: " + e);
            }
        }
    }


    @Override
    public void showInputWarning() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}