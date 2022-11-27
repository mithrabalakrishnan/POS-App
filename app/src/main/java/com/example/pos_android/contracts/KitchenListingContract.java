package com.example.pos_android.contracts;

import com.example.pos_android.data.model.kitchen.KitchenOrderResponse;

public interface KitchenListingContract {
     interface View extends BaseView {
        void showKitchenOrderListApiSuccess(KitchenOrderResponse saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callKitchenOrderList();
        void onKitchenOrderListApiResponse(KitchenOrderResponse saveResponse);
    }
}
