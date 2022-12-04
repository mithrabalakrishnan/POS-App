package com.example.pos_android.contracts;

import com.example.pos_android.data.model.LoginResponse;

public interface AddKitchenUserContract {
        interface View extends BaseView {

        void showSuccess(String message);
        void showInputWarning();


    }

    interface Presenter extends BasePresenter {
        void callAddKitchenUser(String firstName, String lastName, String userName,String phone,
                                String email, String password);
       // void onApiResponse(KitchenResponce saveResponse);


    }
}
