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

public class FoodListFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private FoodListFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private FoodListFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static FoodListFragmentArgs fromBundle(@NonNull Bundle bundle) {
    FoodListFragmentArgs __result = new FoodListFragmentArgs();
    bundle.setClassLoader(FoodListFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("table_info")) {
      TableInfoModel tableInfo;
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        tableInfo = (TableInfoModel) bundle.get("table_info");
      } else {
        throw new UnsupportedOperationException(TableInfoModel.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("table_info", tableInfo);
    } else {
      throw new IllegalArgumentException("Required argument \"table_info\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static FoodListFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    FoodListFragmentArgs __result = new FoodListFragmentArgs();
    if (savedStateHandle.contains("table_info")) {
      TableInfoModel tableInfo;
      tableInfo = savedStateHandle.get("table_info");
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("table_info", tableInfo);
    } else {
      throw new IllegalArgumentException("Required argument \"table_info\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public TableInfoModel getTableInfo() {
    return (TableInfoModel) arguments.get("table_info");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("table_info")) {
      TableInfoModel tableInfo = (TableInfoModel) arguments.get("table_info");
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || tableInfo == null) {
        __result.putParcelable("table_info", Parcelable.class.cast(tableInfo));
      } else if (Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        __result.putSerializable("table_info", Serializable.class.cast(tableInfo));
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
    if (arguments.containsKey("table_info")) {
      TableInfoModel tableInfo = (TableInfoModel) arguments.get("table_info");
      if (Parcelable.class.isAssignableFrom(TableInfoModel.class) || tableInfo == null) {
        __result.set("table_info", Parcelable.class.cast(tableInfo));
      } else if (Serializable.class.isAssignableFrom(TableInfoModel.class)) {
        __result.set("table_info", Serializable.class.cast(tableInfo));
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
    FoodListFragmentArgs that = (FoodListFragmentArgs) object;
    if (arguments.containsKey("table_info") != that.arguments.containsKey("table_info")) {
      return false;
    }
    if (getTableInfo() != null ? !getTableInfo().equals(that.getTableInfo()) : that.getTableInfo() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getTableInfo() != null ? getTableInfo().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FoodListFragmentArgs{"
        + "tableInfo=" + getTableInfo()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull FoodListFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull TableInfoModel tableInfo) {
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_info", tableInfo);
    }

    @NonNull
    public FoodListFragmentArgs build() {
      FoodListFragmentArgs result = new FoodListFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setTableInfo(@NonNull TableInfoModel tableInfo) {
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_info", tableInfo);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public TableInfoModel getTableInfo() {
      return (TableInfoModel) arguments.get("table_info");
    }
  }
}
