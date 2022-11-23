package com.example.pos_android.view.user;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.example.pos_android.data.model.OrderInfoModel;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PaymentFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private PaymentFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private PaymentFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static PaymentFragmentArgs fromBundle(@NonNull Bundle bundle) {
    PaymentFragmentArgs __result = new PaymentFragmentArgs();
    bundle.setClassLoader(PaymentFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("order_info")) {
      OrderInfoModel orderInfo;
      if (Parcelable.class.isAssignableFrom(OrderInfoModel.class) || Serializable.class.isAssignableFrom(OrderInfoModel.class)) {
        orderInfo = (OrderInfoModel) bundle.get("order_info");
      } else {
        throw new UnsupportedOperationException(OrderInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("order_info", orderInfo);
    } else {
      throw new IllegalArgumentException("Required argument \"order_info\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static PaymentFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    PaymentFragmentArgs __result = new PaymentFragmentArgs();
    if (savedStateHandle.contains("order_info")) {
      OrderInfoModel orderInfo;
      orderInfo = savedStateHandle.get("order_info");
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("order_info", orderInfo);
    } else {
      throw new IllegalArgumentException("Required argument \"order_info\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public OrderInfoModel getOrderInfo() {
    return (OrderInfoModel) arguments.get("order_info");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("order_info")) {
      OrderInfoModel orderInfo = (OrderInfoModel) arguments.get("order_info");
      if (Parcelable.class.isAssignableFrom(OrderInfoModel.class) || orderInfo == null) {
        __result.set("order_info", Parcelable.class.cast(orderInfo));
      } else if (Serializable.class.isAssignableFrom(OrderInfoModel.class)) {
        __result.set("order_info", Serializable.class.cast(orderInfo));
      } else {
        throw new UnsupportedOperationException(OrderInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    PaymentFragmentArgs that = (PaymentFragmentArgs) object;
    if (arguments.containsKey("order_info") != that.arguments.containsKey("order_info")) {
      return false;
    }
    if (getOrderInfo() != null ? !getOrderInfo().equals(that.getOrderInfo()) : that.getOrderInfo() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getOrderInfo() != null ? getOrderInfo().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "PaymentFragmentArgs{"
        + "orderInfo=" + getOrderInfo()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull PaymentFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull OrderInfoModel orderInfo) {
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("order_info", orderInfo);
    }

    @NonNull
    public PaymentFragmentArgs build() {
      PaymentFragmentArgs result = new PaymentFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setOrderInfo(@NonNull OrderInfoModel orderInfo) {
      if (orderInfo == null) {
        throw new IllegalArgumentException("Argument \"order_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("order_info", orderInfo);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public OrderInfoModel getOrderInfo() {
      return (OrderInfoModel) arguments.get("order_info");
    }
  }
}
