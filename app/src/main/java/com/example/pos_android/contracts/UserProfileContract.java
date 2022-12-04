package com.example.pos_android.contracts;

import com.example.pos_android.data.model.UserProfileResponse;

public interface UserProfileContract {
          interface View extends BaseView {
        void showUserProfileResponse( UserProfileResponse response);
    }

    interface Presenter extends BasePresenter {

        void getUserProfile();
        void onUserProfileResponseCallback(UserProfileResponse response);

    }
}
