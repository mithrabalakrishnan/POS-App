package com.example.pos_android.contracts;

import com.example.pos_android.data.model.RegisterResponse;

public interface RegisterContract {

    interface View extends BaseView {
        void showSuccess(String message);
    }

    interface Presenter extends BasePresenter {

        void doRegister(String firstName, String lastName, String userName,String phone,
                        String email, String password);

        void onApiResponse(RegisterResponse saveResponse);

    }
}
