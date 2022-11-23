package com.example.pos_android.view.user;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionHomeFragmentToTableReservationFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_tableReservationFragment);
  }
}
