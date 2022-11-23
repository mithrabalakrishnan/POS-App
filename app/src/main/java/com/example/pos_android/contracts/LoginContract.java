package com.example.pos_android.contracts;

import com.example.pos_android.data.model.LoginResponse;

public interface LoginContract {
    interface View extends BaseView {

        void showSuccess(String message);
        void showInputWarning();


    }

    interface Presenter extends BasePresenter {
        void callLogin(String user , String password);
        void onApiResponse(LoginResponse saveResponse);


    }
}
