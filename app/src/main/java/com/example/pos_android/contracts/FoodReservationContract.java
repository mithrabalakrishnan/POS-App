package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.OrderInfoModel;
import com.example.pos_android.data.model.RecaptchaVerifyResponse;
import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.food.FoodOrderResponseModel;

import java.util.Map;

public interface FoodReservationContract {

    interface View extends BaseView {
        void showTableOrderSuccessResponse(FoodOrderResponseModel tableReservationResponse);
        void showCaptchaVerifyCallback(RecaptchaVerifyResponse recaptchaVerifyResponse);
    }

    interface Presenter extends BasePresenter {

        void doFoodReservation(OrderInfoModel orderInfoModel);
        void onAddFoodResponseCallback(FoodOrderResponseModel tableReservationResponse);

        void callCaptchaVerify(Map<String, String> params);
        void onCaptchaVerifyCallback(RecaptchaVerifyResponse recaptchaVerifyResponse);
    }
}
