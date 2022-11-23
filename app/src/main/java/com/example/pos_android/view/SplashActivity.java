package com.example.pos_android.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pos_android.R;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.view.admin.AdminHomeActivity;
import com.example.pos_android.view.login.LoginActivity;
import com.example.pos_android.view.user.UserHomeActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(this);
        new Handler().postDelayed(() -> {

            if (sessionManager.isLoggedIn()) {
                if (sessionManager.getUserType().equals(SessionManager.UserRoles.ADMIN.toString())) {
                    Intent i = new Intent(SplashActivity.this,
                            AdminHomeActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this,
                            UserHomeActivity.class);
                    startActivity(i);
                }
                finishAffinity();
            } else {
                Intent i = new Intent(SplashActivity.this,
                        LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }

        }, SPLASH_SCREEN_TIME_OUT);
    }
}