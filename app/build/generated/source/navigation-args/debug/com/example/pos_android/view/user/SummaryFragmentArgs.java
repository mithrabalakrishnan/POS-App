package com.example.pos_android.view.user;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.example.pos_android.data.model.TableInfoModel;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SummaryFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private SummaryFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private SummaryFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static SummaryFragmentArgs fromBundle(@NonNull Bundle bundle) {
    SummaryFragmentArgs __result = new SummaryFragmentArgs();
    bundle.setClassLoader(SummaryFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("table_summary")) {
      TableInfoModel tableSummary;
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        tableSummary = (TableInfoModel) bundle.get("table_summary");
      } else {
        throw new UnsupportedOperationException(TableInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("table_summary", tableSummary);
    } else {
      throw new IllegalArgumentException("Required argument \"table_summary\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static SummaryFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    SummaryFragmentArgs __result = new SummaryFragmentArgs();
    if (savedStateHandle.contains("table_summary")) {
      TableInfoModel tableSummary;
      tableSummary = savedStateHandle.get("table_summary");
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("table_summary", tableSummary);
    } else {
      throw new IllegalArgumentException("Required argument \"table_summary\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public TableInfoModel getTableSummary() {
    return (TableInfoModel) arguments.get("table_summary");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("table_summary")) {
      TableInfoModel tableSummary = (TableInfoModel) arguments.get("table_summary");
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || tableSummary == null) {
        __result.putParcelable("table_summary", Parcelable.class.cast(tableSummary));
      } else if (Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        __result.putSerializable("table_summary", Serializable.class.cast(tableSummary));
      } else {
        throw new UnsupportedOperationException(TableInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("table_summary")) {
      TableInfoModel tableSummary = (TableInfoModel) arguments.get("table_summary");
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || tableSummary == null) {
        __result.set("table_summary", Parcelable.class.cast(tableSummary));
      } else if (Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        __result.set("table_summary", Serializable.class.cast(tableSummary));
      } else {
        throw new UnsupportedOperationException(TableInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    SummaryFragmentArgs that = (SummaryFragmentArgs) object;
    if (arguments.containsKey("table_summary") != that.arguments.containsKey("table_summary")) {
      return false;
    }
    if (getTableSummary() != null ? !getTableSummary().equals(that.getTableSummary()) : that.getTableSummary() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getTableSummary() != null ? getTableSummary().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SummaryFragmentArgs{"
        + "tableSummary=" + getTableSummary()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull SummaryFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull TableInfoModel tableSummary) {
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_summary", tableSummary);
    }

    @NonNull
    public SummaryFragmentArgs build() {
      SummaryFragmentArgs result = new SummaryFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setTableSummary(@NonNull TableInfoModel tableSummary) {
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_summary", tableSummary);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public TableInfoModel getTableSummary() {
      return (TableInfoModel) arguments.get("table_summary");
    }
  }
}
