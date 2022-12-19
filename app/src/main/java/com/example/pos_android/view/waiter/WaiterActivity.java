package com.example.pos_android.view.waiter;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pos_android.R;
import com.example.pos_android.adapter.WaiterOrderListingAdapter;
import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityWaiterBinding;
import com.example.pos_android.presenter.KitchenPresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.kitchen.KitchenActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WaiterActivity extends BaseActivity implements KitchenListingContract.View, OnItemClickListener {
    ActivityWaiterBinding binding;
    private KitchenPresenter presenter;
    private SessionManager sessionManager;
    private WaiterOrderListingAdapter waiterOrderListingAdapter;
    private List<KitchenResponse.KitchenData> kitchenDataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityWaiterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        showShimmer();
        presenter = new KitchenPresenter(this, this);
        sessionManager = new SessionManager(this);
        waiterOrderListingAdapter = new WaiterOrderListingAdapter(kitchenDataList, this);
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rvOrder.setAdapter(waiterOrderListingAdapter);
        binding.btnLogout.setOnClickListener(v -> {
            showLogoutDialog();
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
        hideShimmer();
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
        showToast(WaiterActivity.this, message);
    }



    @Override
    public void showUserProfileResponse(UserProfileResponse response) {
        binding.tvUser.setText("Hi, " + response.getData().username);
    }

    @Override
    public void showKitchenOrderListApiSuccess(List<KitchenResponse.KitchenData> saveResponse) {
        hideShimmer();
        if (saveResponse.size() > 0) {
            binding.rvOrder.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
            kitchenDataList.clear();
            try {
                Calendar cal = Calendar.getInstance();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("Today's date is " + dateFormat.format(cal.getTime()));
                for (KitchenResponse.KitchenData data : saveResponse) {
                    if (dateFormat.parse(data.getDate()).after(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))) {
                        kitchenDataList.add(data);
                    }
                    waiterOrderListingAdapter.notifyDataSetChanged();
                }
            } catch (ParseException e) {
                Log.d("Date exception", "showAllVouchers: " + e);
            }
        }else{
            binding.rvOrder.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showInputWarning() {

    }

    @Override
    public void onItemClick(Integer position, String from) {
     KitchenResponse.KitchenData data = kitchenDataList.get(position);
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (dateFormat.format(cal.getTime()).equals(data.getDate())) {
            Intent i = new Intent(this, WaiterOrderDetailActivity.class);
            i.putExtra("data", data);
            startActivity(i);
        } else
            showSnackBar(binding.getRoot(), "Can't update order on before or after today");

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    protected void onResume() {
        super.onResume();
        showShimmer();
        presenter.callKitchenOrderList();
        presenter.getUserProfile();
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

    private void showShimmer() {
        binding.layoutKWItemShimmer.setVisibility(View.VISIBLE);
        binding.layoutKWItemShimmer.startShimmer();
        binding.rvOrder.setVisibility(View.GONE);
    }

    private void hideShimmer() {
        binding.layoutKWItemShimmer.setVisibility(View.GONE);
        binding.layoutKWItemShimmer.stopShimmer();
        binding.rvOrder.setVisibility(View.VISIBLE);
    }

}
