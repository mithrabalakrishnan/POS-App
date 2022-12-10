package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.KitchenListingContract;
import com.example.pos_android.contracts.KitchenOrderDetailContract;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.KitchenUpdateStatusResponse;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.model.request.KitchenRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class KitchenPresenter implements KitchenListingContract.Presenter, KitchenOrderDetailContract.Presenter {
    KitchenListingContract.View mView;
    KitchenOrderDetailContract.View detailView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public KitchenPresenter(KitchenListingContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.mView = mView;
        sessionManager = new SessionManager(context);
    }

    public KitchenPresenter(KitchenOrderDetailContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.detailView = mView;
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

            mApiDataManager.getKitchenGetOrders(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void getUserProfile() {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mApiDataManager.getKitchenUserDetails(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onUserProfileResponseCallback(UserProfileResponse response) {
        if (response.isStatus()) {
            mView.showUserProfileResponse(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }

    @Override
    public void onKitchenOrderListApiResponse(KitchenResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showKitchenOrderListApiSuccess(saveResponse.getData());
        } else
            mView.showWarningMessage(saveResponse.getMessage());
    }

    @Override
    public void updateKitchenOrder(KitchenResponse.KitchenData data, String status) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            detailView.showProgressBar();

            KitchenRequestData requestData = new KitchenRequestData(
                    data.getId(), data.getUserId(), status
            );

            mApiDataManager.updateKitchenOrder(sessionManager.getUserToken(), requestData, this);

        } else
            detailView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onKitchenOrderDetailApiResponse(KitchenUpdateStatusResponse saveResponse) {
        detailView.hideProgressBar();
        if (saveResponse.getStatus()) {
            detailView.showSuccessResponse(saveResponse);
        } else
            detailView.showWarningMessage(saveResponse.getMessage());
    }
}
