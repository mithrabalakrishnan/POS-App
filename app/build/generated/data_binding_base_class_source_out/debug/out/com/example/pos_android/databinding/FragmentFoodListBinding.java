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
import androidx.constraintlayout.utils.widget.MotionButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFoodListBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MotionButton btnContinue;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ShimmerFrameLayout layoutCategoryItemShimmer;

  @NonNull
  public final ShimmerFrameLayout layoutCategoryShimmer;

  @NonNull
  public final RecyclerView rvCategories;

  @NonNull
  public final RecyclerView rvCategoriesItems;

  @NonNull
  public final RecyclerView rvPopular;

  @NonNull
  public final RecyclerView rvRecommended;

  @NonNull
  public final TextView titleCategory;

  @NonNull
  public final LinearLayout toolbar;

  private FragmentFoodListBinding(@NonNull RelativeLayout rootView,
      @NonNull MotionButton btnContinue, @NonNull ImageView ivBack,
      @NonNull ShimmerFrameLayout layoutCategoryItemShimmer,
      @NonNull ShimmerFrameLayout layoutCategoryShimmer, @NonNull RecyclerView rvCategories,
      @NonNull RecyclerView rvCategoriesItems, @NonNull RecyclerView rvPopular,
      @NonNull RecyclerView rvRecommended, @NonNull TextView titleCategory,
      @NonNull LinearLayout toolbar) {
    this.rootView = rootView;
    this.btnContinue = btnContinue;
    this.ivBack = ivBack;
    this.layoutCategoryItemShimmer = layoutCategoryItemShimmer;
    this.layoutCategoryShimmer = layoutCategoryShimmer;
    this.rvCategories = rvCategories;
    this.rvCategoriesItems = rvCategoriesItems;
    this.rvPopular = rvPopular;
    this.rvRecommended = rvRecommended;
    this.titleCategory = titleCategory;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFoodListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFoodListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_food_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFoodListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_continue;
      MotionButton btnContinue = ViewBindings.findChildViewById(rootView, id);
      if (btnContinue == null) {
        break missingId;
      }

      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.layout_category_item_shimmer;
      ShimmerFrameLayout layoutCategoryItemShimmer = ViewBindings.findChildViewById(rootView, id);
      if (layoutCategoryItemShimmer == null) {
        break missingId;
      }

      id = R.id.layout_category_shimmer;
      ShimmerFrameLayout layoutCategoryShimmer = ViewBindings.findChildViewById(rootView, id);
      if (layoutCategoryShimmer == null) {
        break missingId;
      }

      id = R.id.rv_categories;
      RecyclerView rvCategories = ViewBindings.findChildViewById(rootView, id);
      if (rvCategories == null) {
        break missingId;
      }

      id = R.id.rv_categories_items;
      RecyclerView rvCategoriesItems = ViewBindings.findChildViewById(rootView, id);
      if (rvCategoriesItems == null) {
        break missingId;
      }

      id = R.id.rv_popular;
      RecyclerView rvPopular = ViewBindings.findChildViewById(rootView, id);
      if (rvPopular == null) {
        break missingId;
      }

      id = R.id.rv_recommended;
      RecyclerView rvRecommended = ViewBindings.findChildViewById(rootView, id);
      if (rvRecommended == null) {
        break missingId;
      }

      id = R.id.title_category;
      TextView titleCategory = ViewBindings.findChildViewById(rootView, id);
      if (titleCategory == null) {
        break missingId;
      }

      id = R.id.toolbar;
      LinearLayout toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new FragmentFoodListBinding((RelativeLayout) rootView, btnContinue, ivBack,
          layoutCategoryItemShimmer, layoutCategoryShimmer, rvCategories, rvCategoriesItems,
          rvPopular, rvRecommended, titleCategory, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
