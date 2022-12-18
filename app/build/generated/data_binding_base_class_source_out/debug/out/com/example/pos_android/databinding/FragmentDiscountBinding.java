// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDiscountBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final LinearLayout layoutTopToolbar;

  @NonNull
  public final LinearLayout noCouponTxt;

  @NonNull
  public final RecyclerView recyclerViewCoupon;

  @NonNull
  public final TextView tvOrderId;

  private FragmentDiscountBinding(@NonNull RelativeLayout rootView, @NonNull ImageView ivBack,
      @NonNull LinearLayout layoutTopToolbar, @NonNull LinearLayout noCouponTxt,
      @NonNull RecyclerView recyclerViewCoupon, @NonNull TextView tvOrderId) {
    this.rootView = rootView;
    this.ivBack = ivBack;
    this.layoutTopToolbar = layoutTopToolbar;
    this.noCouponTxt = noCouponTxt;
    this.recyclerViewCoupon = recyclerViewCoupon;
    this.tvOrderId = tvOrderId;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDiscountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDiscountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_discount, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDiscountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.layout_top_toolbar;
      LinearLayout layoutTopToolbar = ViewBindings.findChildViewById(rootView, id);
      if (layoutTopToolbar == null) {
        break missingId;
      }

      id = R.id.no_coupon_txt;
      LinearLayout noCouponTxt = ViewBindings.findChildViewById(rootView, id);
      if (noCouponTxt == null) {
        break missingId;
      }

      id = R.id.recyclerView_coupon;
      RecyclerView recyclerViewCoupon = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewCoupon == null) {
        break missingId;
      }

      id = R.id.tv_order_id;
      TextView tvOrderId = ViewBindings.findChildViewById(rootView, id);
      if (tvOrderId == null) {
        break missingId;
      }

      return new FragmentDiscountBinding((RelativeLayout) rootView, ivBack, layoutTopToolbar,
          noCouponTxt, recyclerViewCoupon, tvOrderId);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
