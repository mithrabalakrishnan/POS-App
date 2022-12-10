package com.example.pos_android.contracts;

import com.example.pos_android.data.model.EditProfileResponse;
import com.example.pos_android.data.model.UserProfileResponse;

public interface UserProfileContract {
    interface View extends BaseView {
        void showUserProfileResponse(UserProfileResponse response);
        void showSuccess(String message);
    }

    interface Presenter extends BasePresenter {

        void getUserProfile();
        void onUserProfileResponseCallback(UserProfileResponse response);
        void updateUserProfile(String firstName, String lastName, String email, String mobile);
        void onUserUpdateProfileResponse(EditProfileResponse response);
    }
}
