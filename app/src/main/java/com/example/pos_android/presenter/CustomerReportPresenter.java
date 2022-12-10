package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.CustomerReportContract;
import com.example.pos_android.data.model.CustomerReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class CustomerReportPresenter implements CustomerReportContract.Presenter {

    CustomerReportContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public CustomerReportPresenter(CustomerReportContract.View mView, Context context) {
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
    public void getReport(String month) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getCustomerReportDetails(sessionManager.getUserToken(), month, this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onReportResponse(CustomerReportResponse response) {
        mView.hideProgressBar();
        if (response.getStatus()) {
            mView.showReportResponse(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }
}
