package com.example.pos_android.contracts;

import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.food.FoodOrderResponseModel;


public interface FoodReservationContract {

    interface View extends BaseView {
        void showTableOrderSuccessResponse(FoodOrderResponseModel tableReservationResponse);

    }

    interface Presenter extends BasePresenter {

        void doFoodReservation(OrderInfoModel orderInfoModel);
        void onAddFoodResponseCallback(FoodOrderResponseModel tableReservationResponse);

    }
}
