package com.example.pos_android.data.model.request;

public class LoginRequestData {
    final String username;
    final String password;

    public LoginRequestData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}