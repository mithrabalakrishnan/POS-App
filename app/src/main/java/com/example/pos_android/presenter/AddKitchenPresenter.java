package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.AddKitchenContract;
import com.example.pos_android.data.model.AddKitchenResponse;
import com.example.pos_android.data.model.request.RegisterRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class AddKitchenPresenter implements AddKitchenContract.Presenter {
    AddKitchenContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;


    public AddKitchenPresenter(AddKitchenContract.View mView, Context context) {
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
    public void addKitchenUser(String firstName, String lastName, String userName, String phone, String email, String password) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();
            RegisterRequestData requestData = new RegisterRequestData(
                    firstName, lastName, userName, phone, email, password
            );

            mApiDataManager.addKitchenUser(requestData, sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onAddUserResponse(AddKitchenResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showSuccess(saveResponse.getMessage());
        }
    }
}
