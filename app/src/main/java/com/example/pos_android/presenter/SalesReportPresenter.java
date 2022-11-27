package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.SalesReportContract;
import com.example.pos_android.data.model.sales_report.SalesReportResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class SalesReportPresenter implements SalesReportContract.Presenter {

    SalesReportContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public SalesReportPresenter(SalesReportContract.View mView, Context context) {
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
    public void callSalesReport(String type) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getSalesReportMonthly(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onSalesReportApiResponse(SalesReportResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.isStatus()) {
            mView.showSuccess(saveResponse);
        } else
            mView.showWarningMessage(saveResponse.getMessage());
    }
}

