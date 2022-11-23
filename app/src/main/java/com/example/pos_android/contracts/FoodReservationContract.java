package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.TableReservationResponse;

public interface FoodReservationContract {

    interface View extends BaseView {
        void showTableOrderSuccessResponse(String message);
    }

    interface Presenter extends BasePresenter {

        void doFoodReservation(OrderInfoModel orderInfoModel);
        void onAddFoodResponseCallback(CommonResponse tableReservationResponse);

    }
}
