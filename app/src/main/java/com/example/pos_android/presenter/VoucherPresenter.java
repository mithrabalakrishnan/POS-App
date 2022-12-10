package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.VoucherContract;
import com.example.pos_android.data.model.AddVoucherResponse;
import com.example.pos_android.data.model.GetVoucherResponse;
import com.example.pos_android.data.model.request.VoucherRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

import java.util.Arrays;
import java.util.List;

public class VoucherPresenter implements VoucherContract.Presenter {
    VoucherContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;
    public List<Integer> voucherImages = Arrays.asList(
            R.drawable.mastercard,
            R.drawable.black_friday,
            R.drawable.offer,
            R.drawable.shopping_bag
    );

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
    public void callAddVoucher(String title, String voucherCode, String discount, String expiryDate) {
        VoucherRequestData voucherData = new VoucherRequestData(
                title, voucherCode, discount, expiryDate
        );
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.addVoucherToUser(voucherData, sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onAddVoucherApiResponse(AddVoucherResponse response) {
        mView.hideProgressBar();
        if (response.getStatus()) {
            mView.showAddVoucherApiResponseSuccess(response.getMessage());
        } else
            mView.showWarningMessage(response.getMessage());
    }

    @Override
    public void getAllVoucher() {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.getAllVouchers(sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onGetVoucherCallBack(GetVoucherResponse response) {
        mView.hideProgressBar();
        if (response.getStatus()) {
            mView.showAllVouchers(response);
        } else
            mView.showWarningMessage(response.getMessage());
    }
}
