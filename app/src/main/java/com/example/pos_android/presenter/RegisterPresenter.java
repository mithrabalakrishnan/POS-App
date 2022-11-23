package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.RegisterContract;
import com.example.pos_android.data.model.RegisterResponse;
import com.example.pos_android.data.model.request.RegisterRequestData;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;

    public RegisterPresenter(RegisterContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.mView = mView;
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }

    @Override
    public void doRegister(String firstName, String lastName, String userName, String phone,
                           String email, String password) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();
            RegisterRequestData requestData = new RegisterRequestData(
                    firstName, lastName, userName, phone,
                    email, password
            );

            mApiDataManager.registerUser(requestData, this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onApiResponse(RegisterResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showSuccess(saveResponse.getMessage());
        }
    }
}
