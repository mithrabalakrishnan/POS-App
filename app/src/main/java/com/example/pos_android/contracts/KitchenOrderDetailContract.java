package com.example.pos_android.contracts;

import com.example.pos_android.data.model.KitchenOrderDetailResponse;

public interface KitchenOrderDetailContract {
        interface View extends BaseView {
        void showKitchenOrderDetailApiSuccess(KitchenOrderDetailResponse saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callKitchenOrderDetail();
        void onKitchenOrderDetailApiResponse(KitchenOrderDetailResponse saveResponse);
    }
}
