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

public final class FragmentSummaryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView buttonUpdate;

  @NonNull
  public final LinearLayout cardTable;

  @NonNull
  public final ImageView imageArrowright;

  @NonNull
  public final ImageView imageSettings;

  @NonNull
  public final ImageView imageVeg;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ImageView ivNoCart;

  @NonNull
  public final LinearLayout layoutContent;

  @NonNull
  public final AddCartViewBinding layoutQuantityControl;

  @NonNull
  public final LinearLayout layoutTotal;

  @NonNull
  public final LinearLayout linearCheckoutoffers;

  @NonNull
  public final LinearLayout linearRowsettings;

  @NonNull
  public final RecyclerView recyclerFoodItems;

  @NonNull
  public final TextView textFoodName;

  @NonNull
  public final TextView textTotal;

  @NonNull
  public final LinearLayout toolbar;

  @NonNull
  public final TextView tvGuestCount;

  @NonNull
  public final TextView tvTable;

  @NonNull
  public final TextView tvTableTime;

  @NonNull
  public final TextView tvTableType;

  @NonNull
  public final TextView txtAddCoupon;

  private FragmentSummaryBinding(@NonNull RelativeLayout rootView, @NonNull TextView buttonUpdate,
      @NonNull LinearLayout cardTable, @NonNull ImageView imageArrowright,
      @NonNull ImageView imageSettings, @NonNull ImageView imageVeg, @NonNull ImageView ivBack,
      @NonNull ImageView ivNoCart, @NonNull LinearLayout layoutContent,
      @NonNull AddCartViewBinding layoutQuantityControl, @NonNull LinearLayout layoutTotal,
      @NonNull LinearLayout linearCheckoutoffers, @NonNull LinearLayout linearRowsettings,
      @NonNull RecyclerView recyclerFoodItems, @NonNull TextView textFoodName,
      @NonNull TextView textTotal, @NonNull LinearLayout toolbar, @NonNull TextView tvGuestCount,
      @NonNull TextView tvTable, @NonNull TextView tvTableTime, @NonNull TextView tvTableType,
      @NonNull TextView txtAddCoupon) {
    this.rootView = rootView;
    this.buttonUpdate = buttonUpdate;
    this.cardTable = cardTable;
    this.imageArrowright = imageArrowright;
    this.imageSettings = imageSettings;
    this.imageVeg = imageVeg;
    this.ivBack = ivBack;
    this.ivNoCart = ivNoCart;
    this.layoutContent = layoutContent;
    this.layoutQuantityControl = layoutQuantityControl;
    this.layoutTotal = layoutTotal;
    this.linearCheckoutoffers = linearCheckoutoffers;
    this.linearRowsettings = linearRowsettings;
    this.recyclerFoodItems = recyclerFoodItems;
    this.textFoodName = textFoodName;
    this.textTotal = textTotal;
    this.toolbar = toolbar;
    this.tvGuestCount = tvGuestCount;
    this.tvTable = tvTable;
    this.tvTableTime = tvTableTime;
    this.tvTableType = tvTableType;
    this.txtAddCoupon = txtAddCoupon;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSummaryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSummaryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_summary, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSummaryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_update;
      TextView buttonUpdate = ViewBindings.findChildViewById(rootView, id);
      if (buttonUpdate == null) {
        break missingId;
      }

      id = R.id.card_table;
      LinearLayout cardTable = ViewBindings.findChildViewById(rootView, id);
      if (cardTable == null) {
        break missingId;
      }

      id = R.id.imageArrowright;
      ImageView imageArrowright = ViewBindings.findChildViewById(rootView, id);
      if (imageArrowright == null) {
        break missingId;
      }

      id = R.id.imageSettings;
      ImageView imageSettings = ViewBindings.findChildViewById(rootView, id);
      if (imageSettings == null) {
        break missingId;
      }

      id = R.id.image_veg;
      ImageView imageVeg = ViewBindings.findChildViewById(rootView, id);
      if (imageVeg == null) {
        break missingId;
      }

      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.iv_no_cart;
      ImageView ivNoCart = ViewBindings.findChildViewById(rootView, id);
      if (ivNoCart == null) {
        break missingId;
      }

      id = R.id.layout_content;
      LinearLayout layoutContent = ViewBindings.findChildViewById(rootView, id);
      if (layoutContent == null) {
        break missingId;
      }

      id = R.id.layoutQuantityControl;
      View layoutQuantityControl = ViewBindings.findChildViewById(rootView, id);
      if (layoutQuantityControl == null) {
        break missingId;
      }
      AddCartViewBinding binding_layoutQuantityControl = AddCartViewBinding.bind(layoutQuantityControl);

      id = R.id.layout_total;
      LinearLayout layoutTotal = ViewBindings.findChildViewById(rootView, id);
      if (layoutTotal == null) {
        break missingId;
      }

      id = R.id.linearCheckoutoffers;
      LinearLayout linearCheckoutoffers = ViewBindings.findChildViewById(rootView, id);
      if (linearCheckoutoffers == null) {
        break missingId;
      }

      id = R.id.linearRowsettings;
      LinearLayout linearRowsettings = ViewBindings.findChildViewById(rootView, id);
      if (linearRowsettings == null) {
        break missingId;
      }

      id = R.id.recycler_food_items;
      RecyclerView recyclerFoodItems = ViewBindings.findChildViewById(rootView, id);
      if (recyclerFoodItems == null) {
        break missingId;
      }

      id = R.id.text_food_name;
      TextView textFoodName = ViewBindings.findChildViewById(rootView, id);
      if (textFoodName == null) {
        break missingId;
      }

      id = R.id.text_total;
      TextView textTotal = ViewBindings.findChildViewById(rootView, id);
      if (textTotal == null) {
        break missingId;
      }

      id = R.id.toolbar;
      LinearLayout toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tv_guest_count;
      TextView tvGuestCount = ViewBindings.findChildViewById(rootView, id);
      if (tvGuestCount == null) {
        break missingId;
      }

      id = R.id.tv_table;
      TextView tvTable = ViewBindings.findChildViewById(rootView, id);
      if (tvTable == null) {
        break missingId;
      }

      id = R.id.tv_table_time;
      TextView tvTableTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTableTime == null) {
        break missingId;
      }

      id = R.id.tv_table_type;
      TextView tvTableType = ViewBindings.findChildViewById(rootView, id);
      if (tvTableType == null) {
        break missingId;
      }

      id = R.id.txtAddCoupon;
      TextView txtAddCoupon = ViewBindings.findChildViewById(rootView, id);
      if (txtAddCoupon == null) {
        break missingId;
      }

      return new FragmentSummaryBinding((RelativeLayout) rootView, buttonUpdate, cardTable,
          imageArrowright, imageSettings, imageVeg, ivBack, ivNoCart, layoutContent,
          binding_layoutQuantityControl, layoutTotal, linearCheckoutoffers, linearRowsettings,
          recyclerFoodItems, textFoodName, textTotal, toolbar, tvGuestCount, tvTable, tvTableTime,
          tvTableType, txtAddCoupon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
