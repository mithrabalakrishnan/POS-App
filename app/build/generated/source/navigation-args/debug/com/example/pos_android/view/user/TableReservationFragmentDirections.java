package com.example.pos_android.view.user;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.pos_android.R;
import com.example.pos_android.data.model.TableInfoModel;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class TableReservationFragmentDirections {
  private TableReservationFragmentDirections() {
  }

  @NonNull
  public static ActionTableReservationFragmentToFoodListFragment actionTableReservationFragmentToFoodListFragment(
      @NonNull TableInfoModel tableInfo) {
    return new ActionTableReservationFragmentToFoodListFragment(tableInfo);
  }

  public static class ActionTableReservationFragmentToFoodListFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionTableReservationFragmentToFoodListFragment(@NonNull TableInfoModel tableInfo) {
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_info", tableInfo);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionTableReservationFragmentToFoodListFragment setTableInfo(
        @NonNull TableInfoModel tableInfo) {
      if (tableInfo == null) {
        throw new IllegalArgumentException("Argument \"table_info\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_info", tableInfo);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_tableReservationFragment_to_foodListFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public TableInfoModel getTableInfo() {
      return (TableInfoModel) arguments.get("table_info");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionTableReservationFragmentToFoodListFragment that = (ActionTableReservationFragmentToFoodListFragment) object;
      if (arguments.containsKey("table_info") != that.arguments.containsKey("table_info")) {
        return false;
      }
      if (getTableInfo() != null ? !getTableInfo().equals(that.getTableInfo()) : that.getTableInfo() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getTableInfo() != null ? getTableInfo().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionTableReservationFragmentToFoodListFragment(actionId=" + getActionId() + "){"
          + "tableInfo=" + getTableInfo()
          + "}";
    }
  }
}
