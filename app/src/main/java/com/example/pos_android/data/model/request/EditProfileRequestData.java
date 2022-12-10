package com.example.pos_android.data.model.request;

public class EditProfileRequestData {
    final String firstName;
    final String lastName;
    final String phone;
    final String email;

    public EditProfileRequestData(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
}
