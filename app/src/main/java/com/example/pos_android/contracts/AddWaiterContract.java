package com.example.pos_android.contracts;

import com.example.pos_android.data.model.AddKitchenResponse;

public interface AddWaiterContract {
       interface View extends BaseView {
        void showSuccess(String response);
    }

    interface Presenter extends BasePresenter {
        void addWaiter(String firstName, String lastName, String userName, String phone, String email, String password);
        void onWaiterResponse(AddKitchenResponse saveResponse);

    }
}
