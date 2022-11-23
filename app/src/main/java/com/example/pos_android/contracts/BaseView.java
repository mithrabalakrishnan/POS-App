package com.example.pos_android.contracts;

public interface BaseView {
    void showProgressBar();
    void hideProgressBar();
    void showApiErrorWarning(String string);
    void showWarningMessage(String message);
}
