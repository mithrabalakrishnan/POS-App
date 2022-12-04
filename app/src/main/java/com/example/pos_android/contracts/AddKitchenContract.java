package com.example.pos_android.contracts;

import com.example.pos_android.data.model.AddKitchenResponse;
import com.example.pos_android.data.model.CommonResponse;

public interface AddKitchenContract {

    interface View extends BaseView {
        void showSuccess(String response);
    }

    interface Presenter extends BasePresenter {
        void addKitchenUser(String firstName, String lastName, String userName, String phone, String email, String password);
        void onAddUserResponse(AddKitchenResponse saveResponse);

    }
}
