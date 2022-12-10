package com.example.pos_android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.pos_android.R;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.view.kitchen.KitchenActivity;
import com.example.pos_android.view.user.UserHomeActivity;

import java.util.concurrent.Executor;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000;
    Executor executor;
    BiometricPrompt biometricPrompt;
    private SessionManager sessionManager;
    private CancellationSignal cancellationSignal;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(this);
        executor = ContextCompat.getMainExecutor(this);

        if (sessionManager.getIsAuthentication()) {
            authenticateUser();
        } else {
            new Handler().postDelayed(() -> {
                Intent i = new Intent(SplashActivity.this,
                        UserHomeActivity.class);
                startActivity(i );

          /*  if (sessionManager.isLoggedIn()) {
                if (sessionManager.getUserType().equals(SessionManager.UserRoles.ADMIN.toString())) {
                    Intent i = new Intent(SplashActivity.this,
                            AdminHomeActivity.class);
                    startActivity(i);
                } else {
                if (sessionManager.getIsAuthentication()){
                authenticateUser()

}else{
                    Intent i = new Intent(SplashActivity.this,
                            UserHomeActivity.class);
                    startActivity(i);
                }
                finishAffinity();
                }
            } else {
                Intent i = new Intent(SplashActivity.this,
                        LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }*/

            }, SPLASH_SCREEN_TIME_OUT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void authenticateUser() {
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Biometric Login")
                .setSubtitle("Authentication is required to continue")
                .setDescription("This app uses biometric authentication to protect your data.")
                .setNegativeButton("Cancel", this.getMainExecutor(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showToast(SplashActivity.this, "Authentication cancelled");
                            }
                        })
                .build();

        biometricPrompt.authenticate(getCancellationSignal(), getMainExecutor(),
                getAuthenticationCallback());
    }

    private CancellationSignal getCancellationSignal() {

        cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
            @Override
            public void onCancel() {
                showToast(SplashActivity.this, "Cancelled via signal");
            }
        });
        return cancellationSignal;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private BiometricPrompt.AuthenticationCallback getAuthenticationCallback() {

        return new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              CharSequence errString) {
                showToast(SplashActivity.this, "Authentication error: " + errString);
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode,
                                             CharSequence helpString) {
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationSucceeded(
                    BiometricPrompt.AuthenticationResult result) {
                // showToast(SplashActivity.this, "Authentication Succeeded");
                Intent i = new Intent(SplashActivity.this,
                        KitchenActivity.class);
                startActivity(i);
                finishAffinity();
                super.onAuthenticationSucceeded(result);
            }
        };
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}