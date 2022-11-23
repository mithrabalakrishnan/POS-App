package com.example.pos_android.view.user;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;
import com.example.pos_android.data.model.OrderInfoModel;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SummaryFragmentDirections {
  private SummaryFragmentDirections() {
  }

  @NonNull
  public static ActionSummaryFragmentToPaymentFragment actionSummaryFragmentToPaymentFragment(
      @NonNull OrderInfoModel orderInfo) {
    return new ActionSummaryFragmentToPaymentFragment(orderInfo);
  }

  public static class ActionSummaryFragmentToPaymentFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionSummaryFragmentToPaymentFragment(@NonNull OrderInfoModel orderInfo) {
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("order_info", orderInfo);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionSummaryFragmentToPaymentFragment setOrderInfo(@NonNull OrderInfoModel orderInfo) {
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("order_info", orderInfo);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("order_info")) {
        OrderInfoModel orderInfo = (OrderInfoModel) arguments.get("order_info");
        if (Parcelable.class.isAssignableFrom(OrderInfoModel.class) || orderInfo == null) {
          __result.putParcelable("order_info", Parcelable.class.cast(orderInfo));
        } else if (Serializable.class.isAssignableFrom(OrderInfoModel.class)) {
          __result.putSerializable("order_info", Serializable.class.cast(orderInfo));
        } else {
          throw new UnsupportedOperationException(OrderInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_summaryFragment_to_paymentFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public OrderInfoModel getOrderInfo() {
      return (OrderInfoModel) arguments.get("order_info");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionSummaryFragmentToPaymentFragment that = (ActionSummaryFragmentToPaymentFragment) object;
      if (arguments.containsKey("order_info") != that.arguments.containsKey("order_info")) {
        return false;
      }
      if (getOrderInfo() != null ? !getOrderInfo().equals(that.getOrderInfo()) : that.getOrderInfo() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getOrderInfo() != null ? getOrderInfo().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionSummaryFragmentToPaymentFragment(actionId=" + getActionId() + "){"
          + "orderInfo=" + getOrderInfo()
          + "}";
    }
  }
}
