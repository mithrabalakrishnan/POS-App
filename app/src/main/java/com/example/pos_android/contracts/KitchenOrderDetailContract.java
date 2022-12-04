package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.KitchenUpdateStatusResponse;

public interface KitchenOrderDetailContract {
    interface View extends BaseView {
        void showSuccessResponse(KitchenUpdateStatusResponse saveResponse);
    }

    interface Presenter extends BasePresenter {
        void updateKitchenOrder(KitchenResponse.KitchenData data,String status);
        void onKitchenOrderDetailApiResponse(KitchenUpdateStatusResponse saveResponse);
    }
}
