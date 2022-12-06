package com.example.pos_android.contracts;


import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.food.foodCategoryResponse;


public interface UserHomeContract {

    interface View extends BaseView {
        void showUserResponse(UserHomeResponse response);
        void showCategoryResponse(foodCategoryResponse foodCategoryResponse);
    }

    interface Presenter extends BasePresenter {

        void getHomeDetails();
        void onHomeResponseCallback(UserHomeResponse userHomeResponse);
        void getCategory();
        void onCategoryResponse(foodCategoryResponse foodCategoryResponse);

    }
}
