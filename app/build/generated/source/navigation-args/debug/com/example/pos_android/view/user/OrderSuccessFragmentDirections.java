package com.example.pos_android.view.user;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;

public class OrderSuccessFragmentDirections {
  private OrderSuccessFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionOrderSuccessFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_orderSuccessFragment_to_homeFragment);
  }
}
