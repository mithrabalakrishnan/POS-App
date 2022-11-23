package com.example.pos_android.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String TAG = SessionManager.class.getSimpleName();

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    Context context;
    private static final String PREF_NAME = "pos_android";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String USER_TOKEN = "userToken";
    private static final String USERNAME = "userName";
    private static final String USERTYPE = "userType";
    private static final String IS_FOOD_ADDED = "is_food_added";

    public SessionManager(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public void setUserToken(String name) {
        editor.putString(USER_TOKEN, name);
        editor.apply();
    }

    public void setUserName(String name) {
        editor.putString(USERNAME, name);
        editor.apply();
    }

    public void setUsertype(UserRoles roles) {
        editor.putString(USERTYPE, String.valueOf(roles));
        editor.apply();
    }

    public void setIsFoodAdded(String name) {
        editor.putString(IS_FOOD_ADDED, name);
        editor.apply();
    }


    public boolean isLoggedIn() {
        return sharedPref.getBoolean(IS_LOGGED_IN, false);
    }

    public String getUserToken() {
        return sharedPref.getString(USER_TOKEN, "");
    }

    public String getUserName() {
        return sharedPref.getString(USERNAME, "");
    }

    public String getUserType() {
        return sharedPref.getString(USERTYPE, "");
    }

    public String getIsFoodAdded() {
        return sharedPref.getString(IS_FOOD_ADDED, "");
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }

    public enum UserRoles{
        ADMIN,USER,KITCHEN
    }
}

