// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class KitchenOrderItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout btnViewOrder;

  @NonNull
  public final LinearLayout itemLayout;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final TextView tvFoodDetails;

  @NonNull
  public final TextView tvOrderId;

  @NonNull
  public final TextView tvOrderStatus;

  private KitchenOrderItemBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout btnViewOrder, @NonNull LinearLayout itemLayout,
      @NonNull TextView tvDate, @NonNull TextView tvFoodDetails, @NonNull TextView tvOrderId,
      @NonNull TextView tvOrderStatus) {
    this.rootView = rootView;
    this.btnViewOrder = btnViewOrder;
    this.itemLayout = itemLayout;
    this.tvDate = tvDate;
    this.tvFoodDetails = tvFoodDetails;
    this.tvOrderId = tvOrderId;
    this.tvOrderStatus = tvOrderStatus;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static KitchenOrderItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static KitchenOrderItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.kitchen_order_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static KitchenOrderItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_view_order;
      LinearLayout btnViewOrder = ViewBindings.findChildViewById(rootView, id);
      if (btnViewOrder == null) {
        break missingId;
      }

      LinearLayout itemLayout = (LinearLayout) rootView;

      id = R.id.tv_date;
      TextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.tv_food_details;
      TextView tvFoodDetails = ViewBindings.findChildViewById(rootView, id);
      if (tvFoodDetails == null) {
        break missingId;
      }

      id = R.id.tv_order_id;
      TextView tvOrderId = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderId == null) {
        break missingId;
      }

      id = R.id.tv_order_status;
      TextView tvOrderStatus = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderStatus == null) {
        break missingId;
      }

      return new KitchenOrderItemBinding((LinearLayout) rootView, btnViewOrder, itemLayout, tvDate,
          tvFoodDetails, tvOrderId, tvOrderStatus);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
