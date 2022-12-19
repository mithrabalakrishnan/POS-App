package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.AddWaiterContract;
import com.example.pos_android.data.model.AddKitchenResponse;
import com.example.pos_android.data.model.request.RegisterRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class AddWaiterPresenter implements AddWaiterContract.Presenter {

     AddWaiterContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public AddWaiterPresenter(AddWaiterContract.View mView, Context mContext) {
        this.mView = mView;
        this.mApiDataManager = new ApiDataManager();
        this.mContext = mContext;
        this.sessionManager = new SessionManager(mContext);
    }

    @Override
    public void addWaiter(String firstName, String lastName, String userName, String phone, String email, String password) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();
            RegisterRequestData requestData = new RegisterRequestData(
                    firstName, lastName, userName, phone, email, password
            );

            mApiDataManager.addWaiterUser(requestData, sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onWaiterResponse(AddKitchenResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showSuccess(saveResponse.getMessage());
        }
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }
}
