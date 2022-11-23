package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.ImagePickerResponse;
import com.example.pos_android.data.model.UserHomeResponse;

import java.io.File;

public interface UserHomeContract {

    interface View extends BaseView {
        void showUserResponse(UserHomeResponse response);
    }

    interface Presenter extends BasePresenter {

        void getHomeDetails();
        void onHomeResponseCallback(UserHomeResponse userHomeResponse);

    }
}
