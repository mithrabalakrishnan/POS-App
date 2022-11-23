package com.example.pos_android.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isNotNullOrEmpty(String text) {
        return (text != null && !TextUtils.isEmpty(text));
    }
    public static boolean isValidPassword(String email) {
        return email.length() >= 6;
    }

    public static boolean isPasswordSecure(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isPasswordConfirm(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
