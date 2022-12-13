package com.example.pos_android.view.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pos_android.R;
import com.example.pos_android.adapter.AllFoodAdapter;
import com.example.pos_android.contracts.UserHomeContract;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.food.CategoryDetailResponse;
import com.example.pos_android.data.model.food.foodCategoryResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.ActivityListAllFoodBinding;
import com.example.pos_android.presenter.UserHomePresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseActivity;
import com.example.pos_android.view.login.LoginActivity;

import java.util.ArrayList;

public class ListAllFoodActivity extends BaseActivity implements UserHomeContract.View, OnItemClickListener {
    UserHomePresenter presenter;
    AllFoodAdapter adapter;
    ArrayList<FoodModel> foodModelArrayList = new ArrayList<>();
    private ActivityListAllFoodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListAllFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        presenter = new UserHomePresenter(this, this);
        presenter.getHomeDetails();
        binding.ivBack.setOnClickListener( v-> {
            onBackPressed();
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
            showToast(this, string);
    }

    @Override
    public void showWarningMessage(String message) {
        showToast(this, message);
    }


    @Override
    public void showUserResponse(UserHomeResponse response) {
        // showToast(requireContext(), response.getMessage());
        foodModelArrayList.clear();
        for (UserHomeResponse.PopularFood food : response.getData().getPopularFoods()) {
            foodModelArrayList.add(new FoodModel(String.valueOf(food.getFoodId()),
                    food.getName(), food.getImage(), food.getPrice()
            ));
        }
        adapter = new AllFoodAdapter(foodModelArrayList, this, this);
        binding.rvRecommended.setAdapter(adapter);
        binding.rvRecommended.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void showCategoryResponse(foodCategoryResponse foodCategoryResponse) {

    }

    @Override
    public void showCategoryItemsResponse(CategoryDetailResponse categoryDetailResponse) {

    }

    @Override
    public void onItemClick(Integer position, String from) {
        FoodModel model = foodModelArrayList.get(position);
        int id = Integer.parseInt(model.getFoodId());
        Intent intent = new Intent(ListAllFoodActivity.this, IncomeReportActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}