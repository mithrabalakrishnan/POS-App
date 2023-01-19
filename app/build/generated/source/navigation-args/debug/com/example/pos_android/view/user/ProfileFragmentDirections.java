package com.example.pos_android.view.user;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragmentToEditProfileFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment_to_editProfileFragment);
  }
}
