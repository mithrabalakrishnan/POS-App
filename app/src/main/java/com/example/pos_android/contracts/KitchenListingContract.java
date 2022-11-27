package com.example.pos_android.contracts;

import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.kitchen.KitchenOrderResponse;

import java.util.List;

public interface KitchenListingContract {
     interface View extends BaseView {
        void showKitchenOrderListApiSuccess(List<KitchenResponse.KitchenData> saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callKitchenOrderList();
        void onKitchenOrderListApiResponse(KitchenResponse saveResponse);
    }
}
