package com.example.pos_android.view.user;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;

public class PaymentFragmentDirections {
  private PaymentFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionPaymentFragmentToOrderSuccessFragment() {
    return new ActionOnlyNavDirections(R.id.action_paymentFragment_to_orderSuccessFragment);
  }
}
