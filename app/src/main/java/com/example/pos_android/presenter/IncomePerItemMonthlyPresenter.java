package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.IncomePerItemMonthlyContract;
import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class IncomePerItemMonthlyPresenter implements IncomePerItemMonthlyContract.Presenter {

       IncomePerItemMonthlyContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public IncomePerItemMonthlyPresenter(IncomePerItemMonthlyContract.View mView, Context context) {
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
    public void geIncomePerItemMonthly(String type) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getIncomePerItemMonthly(sessionManager.getUserToken(), this, type);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onIncomePerItemMonthlyCallback(IncomePerItemMonthlyResponse response) {
        mView.hideProgressBar();
        if (response.isSuccess()) {
            mView.showIncomePerItemMonthlyResponse(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }
}
