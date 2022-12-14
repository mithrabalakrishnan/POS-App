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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class IncDecViewBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final MaterialButton btnDelete;

  @NonNull
  public final ImageView imageAdd;

  @NonNull
  public final ImageView imageSub;

  @NonNull
  public final LinearLayout root;

  @NonNull
  public final TextView textQuantity;

  private IncDecViewBinding(@NonNull MaterialCardView rootView, @NonNull MaterialButton btnDelete,
      @NonNull ImageView imageAdd, @NonNull ImageView imageSub, @NonNull LinearLayout root,
      @NonNull TextView textQuantity) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.imageAdd = imageAdd;
    this.imageSub = imageSub;
    this.root = root;
    this.textQuantity = textQuantity;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static IncDecViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static IncDecViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.inc_dec_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static IncDecViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_delete;
      MaterialButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.image_add;
      ImageView imageAdd = ViewBindings.findChildViewById(rootView, id);
      if (imageAdd == null) {
        break missingId;
      }

      id = R.id.image_sub;
      ImageView imageSub = ViewBindings.findChildViewById(rootView, id);
      if (imageSub == null) {
        break missingId;
      }

      id = R.id.root;
      LinearLayout root = ViewBindings.findChildViewById(rootView, id);
      if (root == null) {
        break missingId;
      }

      id = R.id.text_quantity;
      TextView textQuantity = ViewBindings.findChildViewById(rootView, id);
      if (textQuantity == null) {
        break missingId;
      }

      return new IncDecViewBinding((MaterialCardView) rootView, btnDelete, imageAdd, imageSub, root,
          textQuantity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
