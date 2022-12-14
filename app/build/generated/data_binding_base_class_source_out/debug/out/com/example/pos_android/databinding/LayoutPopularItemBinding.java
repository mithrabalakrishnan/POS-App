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

public final class LayoutPopularItemBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView likesTextView;

  @NonNull
  public final ImageView subjectImageView;

  @NonNull
  public final TextView subjectTextView;

  private LayoutPopularItemBinding(@NonNull LinearLayout rootView, @NonNull TextView likesTextView,
      @NonNull ImageView subjectImageView, @NonNull TextView subjectTextView) {
    this.rootView = rootView;
    this.likesTextView = likesTextView;
    this.subjectImageView = subjectImageView;
    this.subjectTextView = subjectTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutPopularItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutPopularItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_popular_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutPopularItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.likes_text_view;
      TextView likesTextView = ViewBindings.findChildViewById(rootView, id);
      if (likesTextView == null) {
        break missingId;
      }

      id = R.id.subject_image_view;
      ImageView subjectImageView = ViewBindings.findChildViewById(rootView, id);
      if (subjectImageView == null) {
        break missingId;
      }

      id = R.id.subject_text_view;
      TextView subjectTextView = ViewBindings.findChildViewById(rootView, id);
      if (subjectTextView == null) {
        break missingId;
      }

      return new LayoutPopularItemBinding((LinearLayout) rootView, likesTextView, subjectImageView,
          subjectTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
