// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MaterialButton btnLogout;

  @NonNull
  public final ImageView ivUser;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvMobile;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvUserName;

  private FragmentProfileBinding(@NonNull RelativeLayout rootView,
      @NonNull MaterialButton btnLogout, @NonNull ImageView ivUser, @NonNull TextView tvEmail,
      @NonNull TextView tvMobile, @NonNull TextView tvName, @NonNull TextView tvUserName) {
    this.rootView = rootView;
    this.btnLogout = btnLogout;
    this.ivUser = ivUser;
    this.tvEmail = tvEmail;
    this.tvMobile = tvMobile;
    this.tvName = tvName;
    this.tvUserName = tvUserName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_logout;
      MaterialButton btnLogout = ViewBindings.findChildViewById(rootView, id);
      if (btnLogout == null) {
        break missingId;
      }

      id = R.id.iv_user;
      ImageView ivUser = ViewBindings.findChildViewById(rootView, id);
      if (ivUser == null) {
        break missingId;
      }

      id = R.id.tv_email;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tv_mobile;
      TextView tvMobile = ViewBindings.findChildViewById(rootView, id);
      if (tvMobile == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = ViewBindings.findChildViewById(rootView, id);
      if (tvName == null) {
        break missingId;
      }

      id = R.id.tv_user_name;
      TextView tvUserName = ViewBindings.findChildViewById(rootView, id);
      if (tvUserName == null) {
        break missingId;
      }

      return new FragmentProfileBinding((RelativeLayout) rootView, btnLogout, ivUser, tvEmail,
          tvMobile, tvName, tvUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
