package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.UserHomeContract;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.food.CategoryDetailResponse;
import com.example.pos_android.data.model.food.CategoryModel;
import com.example.pos_android.data.model.food.foodCategoryResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class UserHomePresenter implements UserHomeContract.Presenter {

    UserHomeContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public UserHomePresenter(UserHomeContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.mView = mView;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
        mView.onApiError();
    }

    @Override
    public void getHomeDetails() {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getUserHomeDetails(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onHomeResponseCallback(UserHomeResponse userHomeResponse) {
        mView.hideProgressBar();
        if (userHomeResponse.getStatus()) {
            mView.showUserResponse(userHomeResponse);
        } else
            mView.showWarningMessage(userHomeResponse.getMessage());
    }

    @Override
    public void getCategory() {
        if (NetworkManager.isNetworkAvailable(mContext)) {

            mApiDataManager.getCategories(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));

    }

    @Override
    public void onCategoryResponse(foodCategoryResponse foodCategoryResponse) {
        if (foodCategoryResponse.isStatus()) {
            mView.showCategoryResponse(foodCategoryResponse);
        } else
            mView.showWarningMessage(foodCategoryResponse.getMessage());
    }

    @Override
    public void getCategoryItems(String category) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getCategoriesItems(sessionManager.getUserToken(), category, this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onCategoryItemsResponse(CategoryDetailResponse categoryDetailResponse) {
        mView.hideProgressBar();
        if (categoryDetailResponse.isStatus()) {
            mView.showCategoryItemsResponse(categoryDetailResponse);
        } else
            mView.showWarningMessage(categoryDetailResponse.getMessage());
    }
}
