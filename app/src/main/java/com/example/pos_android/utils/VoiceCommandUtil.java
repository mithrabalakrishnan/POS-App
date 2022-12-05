package com.example.pos_android.utils;

import androidx.navigation.NavController;

import com.example.pos_android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VoiceCommandUtil {

    final String command;
    BottomNavigationView bottomNavigationView;
    NavController navController;

    public VoiceCommandUtil(String command, BottomNavigationView bottomNavigationView, NavController navController) {
        this.command = command;
        this.bottomNavigationView = bottomNavigationView;
        this.navController = navController;
        performCommand(command);
    }

    public void performCommand(String command) {
        switch (command) {
            case "GO TO DISCOUNT": {
                bottomNavigationView.setSelectedItemId(R.id.discountFragment);
                break;
            }
            case "GO TO MY ORDERS": {
                bottomNavigationView.setSelectedItemId(R.id.orderFragment);
                break;
            }
            case "GO TO PROFILE": {
                bottomNavigationView.setSelectedItemId(R.id.profileFragment);
                break;
            }
            case "BOOK TABLE": {
                navController.navigate(R.id.tableReservationFragment);
                break;
            }
            default: {
            }
        }
    }

}
