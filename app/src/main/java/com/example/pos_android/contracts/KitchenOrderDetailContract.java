package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;

public interface KitchenOrderDetailContract {
        interface View extends BaseView {
        void showKitchenOrderDetailApiSuccess(CommonResponse saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callKitchenOrderDetail();
        void onKitchenOrderDetailApiResponse(CommonResponse saveResponse);
    }
}
