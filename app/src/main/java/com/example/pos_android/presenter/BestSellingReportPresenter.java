package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.BestSellingReportContract;
import com.example.pos_android.data.model.sales_report.BestSellingReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class BestSellingReportPresenter implements BestSellingReportContract.Presenter {
    BestSellingReportContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public BestSellingReportPresenter(BestSellingReportContract.View mView, Context context) {
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
    public void getBestSellingReport(String type) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getBestSellingReport(sessionManager.getUserToken(), this, type);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onBestSellingReportCallback(BestSellingReportResponse response) {
        mView.hideProgressBar();
        if (response.isSuccess()) {
            mView.showBestSellingReportResponse(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }
}
