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

public class FoodListFragmentDirections {
  private FoodListFragmentDirections() {
  }

  @NonNull
  public static ActionFoodListFragmentToSummaryFragment actionFoodListFragmentToSummaryFragment(
      @NonNull TableInfoModel tableSummary) {
    return new ActionFoodListFragmentToSummaryFragment(tableSummary);
  }

  public static class ActionFoodListFragmentToSummaryFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionFoodListFragmentToSummaryFragment(@NonNull TableInfoModel tableSummary) {
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_summary", tableSummary);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionFoodListFragmentToSummaryFragment setTableSummary(
        @NonNull TableInfoModel tableSummary) {
      if (tableSummary == null) {
        throw new IllegalArgumentException("Argument \"table_summary\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("table_summary", tableSummary);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_foodListFragment_to_summaryFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public TableInfoModel getTableSummary() {
      return (TableInfoModel) arguments.get("table_summary");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionFoodListFragmentToSummaryFragment that = (ActionFoodListFragmentToSummaryFragment) object;
      if (arguments.containsKey("table_summary") != that.arguments.containsKey("table_summary")) {
        return false;
      }
      if (getTableSummary() != null ? !getTableSummary().equals(that.getTableSummary()) : that.getTableSummary() != null) {
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
      result = 31 * result + (getTableSummary() != null ? getTableSummary().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionFoodListFragmentToSummaryFragment(actionId=" + getActionId() + "){"
          + "tableSummary=" + getTableSummary()
          + "}";
    }
  }
}
