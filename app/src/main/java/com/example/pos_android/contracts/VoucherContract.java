package com.example.pos_android.contracts;

public interface VoucherContract {

    interface View extends BaseView {
//        void showAddVoucherApiResponseSuccess();
//        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callAddVoucher(String name, String Category, String Price);
        void onAddVoucherApiResponse();
    }
}
