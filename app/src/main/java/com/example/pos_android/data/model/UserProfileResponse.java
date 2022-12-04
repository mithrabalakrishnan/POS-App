package com.example.pos_android.data.model;

public class UserProfileResponse {
    public boolean status;
    public String message;
    public UserProfileData data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfileData getData() {
        return data;
    }

    public void setData(UserProfileData data) {
        this.data = data;
    }
}
