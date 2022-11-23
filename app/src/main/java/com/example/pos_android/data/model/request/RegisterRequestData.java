package com.example.pos_android.data.model.request;

public class RegisterRequestData {
    final String firstName;
    final String lastName;
    final String username;
    final String phone;
    final String email;
    final String password;

    public RegisterRequestData(String firstName, String lastName, String username, String phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
}
