// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddWaiterBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnAddWaiter;

  @NonNull
  public final TextInputLayout emailLayout;

  @NonNull
  public final TextInputEditText etEmail;

  @NonNull
  public final TextInputEditText etFirstName;

  @NonNull
  public final TextInputEditText etLastName;

  @NonNull
  public final TextInputEditText etMobile;

  @NonNull
  public final TextInputEditText etPassword;

  @NonNull
  public final TextInputEditText etUsername;

  @NonNull
  public final TextInputLayout firstNameLayout;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final TextInputLayout lastNameLayout;

  @NonNull
  public final TextInputLayout mobileLayout;

  @NonNull
  public final TextInputLayout passwordLayout;

  @NonNull
  public final TextInputLayout usernameLayout;

  private ActivityAddWaiterBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialButton btnAddWaiter, @NonNull TextInputLayout emailLayout,
      @NonNull TextInputEditText etEmail, @NonNull TextInputEditText etFirstName,
      @NonNull TextInputEditText etLastName, @NonNull TextInputEditText etMobile,
      @NonNull TextInputEditText etPassword, @NonNull TextInputEditText etUsername,
      @NonNull TextInputLayout firstNameLayout, @NonNull ImageView ivBack,
      @NonNull TextInputLayout lastNameLayout, @NonNull TextInputLayout mobileLayout,
      @NonNull TextInputLayout passwordLayout, @NonNull TextInputLayout usernameLayout) {
    this.rootView = rootView;
    this.btnAddWaiter = btnAddWaiter;
    this.emailLayout = emailLayout;
    this.etEmail = etEmail;
    this.etFirstName = etFirstName;
    this.etLastName = etLastName;
    this.etMobile = etMobile;
    this.etPassword = etPassword;
    this.etUsername = etUsername;
    this.firstNameLayout = firstNameLayout;
    this.ivBack = ivBack;
    this.lastNameLayout = lastNameLayout;
    this.mobileLayout = mobileLayout;
    this.passwordLayout = passwordLayout;
    this.usernameLayout = usernameLayout;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddWaiterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddWaiterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_waiter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddWaiterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_waiter;
      MaterialButton btnAddWaiter = ViewBindings.findChildViewById(rootView, id);
      if (btnAddWaiter == null) {
        break missingId;
      }

      id = R.id.email_layout;
      TextInputLayout emailLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailLayout == null) {
        break missingId;
      }

      id = R.id.et_email;
      TextInputEditText etEmail = ViewBindings.findChildViewById(rootView, id);
      if (etEmail == null) {
        break missingId;
      }

      id = R.id.et_first_name;
      TextInputEditText etFirstName = ViewBindings.findChildViewById(rootView, id);
      if (etFirstName == null) {
        break missingId;
      }

      id = R.id.et_last_name;
      TextInputEditText etLastName = ViewBindings.findChildViewById(rootView, id);
      if (etLastName == null) {
        break missingId;
      }

      id = R.id.et_mobile;
      TextInputEditText etMobile = ViewBindings.findChildViewById(rootView, id);
      if (etMobile == null) {
        break missingId;
      }

      id = R.id.et_password;
      TextInputEditText etPassword = ViewBindings.findChildViewById(rootView, id);
      if (etPassword == null) {
        break missingId;
      }

      id = R.id.et_username;
      TextInputEditText etUsername = ViewBindings.findChildViewById(rootView, id);
      if (etUsername == null) {
        break missingId;
      }

      id = R.id.first_name_layout;
      TextInputLayout firstNameLayout = ViewBindings.findChildViewById(rootView, id);
      if (firstNameLayout == null) {
        break missingId;
      }

      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.last_name_layout;
      TextInputLayout lastNameLayout = ViewBindings.findChildViewById(rootView, id);
      if (lastNameLayout == null) {
        break missingId;
      }

      id = R.id.mobile_layout;
      TextInputLayout mobileLayout = ViewBindings.findChildViewById(rootView, id);
      if (mobileLayout == null) {
        break missingId;
      }

      id = R.id.password_layout;
      TextInputLayout passwordLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordLayout == null) {
        break missingId;
      }

      id = R.id.username_layout;
      TextInputLayout usernameLayout = ViewBindings.findChildViewById(rootView, id);
      if (usernameLayout == null) {
        break missingId;
      }

      return new ActivityAddWaiterBinding((LinearLayout) rootView, btnAddWaiter, emailLayout,
          etEmail, etFirstName, etLastName, etMobile, etPassword, etUsername, firstNameLayout,
          ivBack, lastNameLayout, mobileLayout, passwordLayout, usernameLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}