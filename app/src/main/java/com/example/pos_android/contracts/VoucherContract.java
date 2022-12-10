package com.example.pos_android.contracts;

import com.example.pos_android.data.model.AddVoucherResponse;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.GetVoucherResponse;

public interface VoucherContract {

    interface View extends BaseView {
        void showAddVoucherApiResponseSuccess(String response);
        void showInputWarning();
        void showAllVouchers(GetVoucherResponse response);
    }
    interface Presenter extends BasePresenter {
        void callAddVoucher(String title, String voucherCode, String discount,String expiryDate);
        void onAddVoucherApiResponse(AddVoucherResponse response);
        void getAllVoucher();
        void onGetVoucherCallBack(GetVoucherResponse response);
    }
}
