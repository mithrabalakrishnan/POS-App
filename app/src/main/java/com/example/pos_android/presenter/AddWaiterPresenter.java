package com.example.pos_android.presenter;

import com.example.pos_android.contracts.AddWaiterContract;
import com.example.pos_android.data.model.AddKitchenResponse;

public class AddWaiterPresenter implements AddWaiterContract.Presenter {
    @Override
    public void addWaiter(String firstName, String lastName, String userName, String phone, String email, String password) {

    }

    @Override
    public void onWaiterResponse(AddKitchenResponse saveResponse) {

    }

    @Override
    public void onApiError(String data) {

    }
}
