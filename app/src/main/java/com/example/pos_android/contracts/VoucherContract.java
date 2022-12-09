package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;

public interface VoucherContract {

    interface View extends BaseView {
        void showAddVoucherApiResponseSuccess(CommonResponse response);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callAddVoucher(String name, String Category, String Price);
        void onAddVoucherApiResponse(CommonResponse response);
    }
}
