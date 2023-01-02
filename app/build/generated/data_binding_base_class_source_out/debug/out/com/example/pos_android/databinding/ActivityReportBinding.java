// Generated by view binder compiler. Do not edit!
package com.example.pos_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pos_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityReportBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnBestSelling;

  @NonNull
  public final Button btnForeCasting;

  @NonNull
  public final Button btnSalesReport;

  @NonNull
  public final Button customerReport;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final Button totalIncomeReport;

  private ActivityReportBinding(@NonNull LinearLayout rootView, @NonNull Button btnBestSelling,
      @NonNull Button btnForeCasting, @NonNull Button btnSalesReport,
      @NonNull Button customerReport, @NonNull ImageView ivBack,
      @NonNull Button totalIncomeReport) {
    this.rootView = rootView;
    this.btnBestSelling = btnBestSelling;
    this.btnForeCasting = btnForeCasting;
    this.btnSalesReport = btnSalesReport;
    this.customerReport = customerReport;
    this.ivBack = ivBack;
    this.totalIncomeReport = totalIncomeReport;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityReportBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityReportBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_report, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityReportBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_best_selling;
      Button btnBestSelling = ViewBindings.findChildViewById(rootView, id);
      if (btnBestSelling == null) {
        break missingId;
      }

      id = R.id.btn_fore_casting;
      Button btnForeCasting = ViewBindings.findChildViewById(rootView, id);
      if (btnForeCasting == null) {
        break missingId;
      }

      id = R.id.btn_sales_report;
      Button btnSalesReport = ViewBindings.findChildViewById(rootView, id);
      if (btnSalesReport == null) {
        break missingId;
      }

      id = R.id.customer_report;
      Button customerReport = ViewBindings.findChildViewById(rootView, id);
      if (customerReport == null) {
        break missingId;
      }

      id = R.id.iv_back;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.total_income_report;
      Button totalIncomeReport = ViewBindings.findChildViewById(rootView, id);
      if (totalIncomeReport == null) {
        break missingId;
      }

      return new ActivityReportBinding((LinearLayout) rootView, btnBestSelling, btnForeCasting,
          btnSalesReport, customerReport, ivBack, totalIncomeReport);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
