package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.data.model.kitchen.KitchenOrderResponse;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class KitchenPresenter implements KitchenListingContract.Presenter {
    KitchenListingContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public KitchenPresenter(KitchenListingContract.View mView, Context context) {
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
    public void callKitchenOrderList() {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getKitchenGetOrders(sessionManager.getUserToken(),this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onKitchenOrderListApiResponse(KitchenOrderResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.isSuccess()) {
            mView.showKitchenOrderListApiSuccess(saveResponse);
        } else
            mView.showWarningMessage(saveResponse.getMessage());
    }
}
