package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.FoodReservationContract;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.request.FoodOrderRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class ConfirmOrderPresenter implements FoodReservationContract.Presenter {

    FoodReservationContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public ConfirmOrderPresenter(FoodReservationContract.View mView, Context context) {
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
    public void doFoodReservation(OrderInfoModel orderInfoModel) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.makeFoodOrder(new FoodOrderRequestData(
                            orderInfoModel.getFoodId(), orderInfoModel.getQuantity(),
                            orderInfoModel.getTableInfoModel().getId(), orderInfoModel.getTotalPrice(),
                            orderInfoModel.getTimeAndDate()
                    ),
                    sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onAddFoodResponseCallback(CommonResponse tableReservationResponse) {
        mView.hideProgressBar();
        if (tableReservationResponse.getStatus()) {
            mView.showTableOrderSuccessResponse("Order Successfully");
        } else
            mView.showWarningMessage(tableReservationResponse.getMessage());
    }
}
