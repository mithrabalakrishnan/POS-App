package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.LoginContract;
import com.example.pos_android.data.model.LoginResponse;
import com.example.pos_android.data.model.request.LoginRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public LoginPresenter(LoginContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        this.mView = mView;
        mContext = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }

    @Override
    public void callLogin(String user, String password) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            LoginRequestData requestData = new LoginRequestData(user, password);
            mApiDataManager.loginUser(requestData, this);

        } else mView.showWarningMessage(mContext.getResources().getString(R.string.no_network));
    }

    @Override
    public void onApiResponse(LoginResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            sessionManager.setUserToken(saveResponse.getData().getToken());
            mView.showSuccess(saveResponse.getMessage());
        } else
            mView.showApiErrorWarning(saveResponse.getMessage());
    }
}
