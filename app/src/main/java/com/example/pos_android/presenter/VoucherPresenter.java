package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.contracts.VoucherContract;
import com.example.pos_android.data.model.VoucherRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;

public class VoucherPresenter implements VoucherContract.Presenter {
    VoucherContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public VoucherPresenter(VoucherContract.View mView, Context mContext) {
        this.mView = mView;
        this.mApiDataManager = new ApiDataManager();
        this.mContext = mContext;
        this.sessionManager = new SessionManager(mContext);
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }

    @Override
    public void callAddVoucher(String name, String Category, String Price) {
         VoucherRequestData voucherData = new VoucherRequestData(
                    name,Category, Price
            );
    }

    @Override
    public void onAddVoucherApiResponse() {

    }
}
