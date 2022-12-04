package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.UserProfileContract;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class UserProfilePresenter implements UserProfileContract.Presenter {
    UserProfileContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public UserProfilePresenter(UserProfileContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.mView = mView;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }

    @Override
    public void getUserProfile() {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.userGetProfile(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onUserProfileResponseCallback(UserProfileResponse response) {
        mView.hideProgressBar();
        if (response.isStatus()) {
            mView.showUserProfileResponse(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }
}
