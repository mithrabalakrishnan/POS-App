// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class ActivityWaiterOrderDetailBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final LinearLayout layoutFoodDetails;

  @NonNull
  public final TextView tvCustomerDetails;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final TextView tvOrderId;

  @NonNull
  public final TextView tvTableId;

  @NonNull
  public final TextView tvTime;

  @NonNull
  public final TextView txtFoodItems;

  @NonNull
  public final TextView txtFoodQty;

  private ActivityWaiterOrderDetailBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView ivBack, @NonNull LinearLayout layoutFoodDetails,
      @NonNull TextView tvCustomerDetails, @NonNull TextView tvDate, @NonNull TextView tvOrderId,
      @NonNull TextView tvTableId, @NonNull TextView tvTime, @NonNull TextView txtFoodItems,
      @NonNull TextView txtFoodQty) {
    this.rootView = rootView;
    this.ivBack = ivBack;
    this.layoutFoodDetails = layoutFoodDetails;
    this.tvCustomerDetails = tvCustomerDetails;
    this.tvDate = tvDate;
    this.tvOrderId = tvOrderId;
    this.tvTableId = tvTableId;
    this.tvTime = tvTime;
    this.txtFoodItems = txtFoodItems;
    this.txtFoodQty = txtFoodQty;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityWaiterOrderDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityWaiterOrderDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_waiter_order_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityWaiterOrderDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.layout_food_details;
      LinearLayout layoutFoodDetails = ViewBindings.findChildViewById(rootView, id);
      if (layoutFoodDetails == null) {
        break missingId;
      }

      id = R.id.tv_customer_details;
      TextView tvCustomerDetails = ViewBindings.findChildViewById(rootView, id);
      if (tvCustomerDetails == null) {
        break missingId;
      }

      id = R.id.tv_date;
      TextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.tv_order_id;
      TextView tvOrderId = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderId == null) {
        break missingId;
      }

      id = R.id.tv_table_Id;
      TextView tvTableId = ViewBindings.findChildViewById(rootView, id);
      if (tvTableId == null) {
        break missingId;
      }

      id = R.id.tv_time;
      TextView tvTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTime == null) {
        break missingId;
      }

      id = R.id.txt_food_items;
      TextView txtFoodItems = ViewBindings.findChildViewById(rootView, id);
      if (txtFoodItems == null) {
        break missingId;
      }

      id = R.id.txt_food_qty;
      TextView txtFoodQty = ViewBindings.findChildViewById(rootView, id);
      if (txtFoodQty == null) {
        break missingId;
      }

      return new ActivityWaiterOrderDetailBinding((LinearLayout) rootView, ivBack,
          layoutFoodDetails, tvCustomerDetails, tvDate, tvOrderId, tvTableId, tvTime, txtFoodItems,
          txtFoodQty);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
