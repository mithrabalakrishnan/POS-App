package com.example.pos_android.utils;

import com.example.pos_android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class VoiceCommandUtil {

    final String command;
    BottomNavigationView bottomNavigationView;

    public VoiceCommandUtil(String command, BottomNavigationView bottomNavigationView) {
        this.command = command;
        this.bottomNavigationView = bottomNavigationView;
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
            default: {
            }
        }
    }

}
