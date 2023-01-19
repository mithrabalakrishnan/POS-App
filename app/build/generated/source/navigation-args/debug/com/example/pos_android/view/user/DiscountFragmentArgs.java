package com.example.pos_android.view.user;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DiscountFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DiscountFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private DiscountFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DiscountFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DiscountFragmentArgs __result = new DiscountFragmentArgs();
    bundle.setClassLoader(DiscountFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("from_page")) {
      String fromPage;
      fromPage = bundle.getString("from_page");
      if (fromPage == null) {
        throw new IllegalArgumentException("Argument \"from_page\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("from_page", fromPage);
    } else {
      __result.arguments.put("from_page", "other");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DiscountFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    DiscountFragmentArgs __result = new DiscountFragmentArgs();
    if (savedStateHandle.contains("from_page")) {
      String fromPage;
      fromPage = savedStateHandle.get("from_page");
      if (fromPage == null) {
        throw new IllegalArgumentException("Argument \"from_page\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("from_page", fromPage);
    } else {
      __result.arguments.put("from_page", "other");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getFromPage() {
    return (String) arguments.get("from_page");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("from_page")) {
      String fromPage = (String) arguments.get("from_page");
      __result.putString("from_page", fromPage);
    } else {
      __result.putString("from_page", "other");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("from_page")) {
      String fromPage = (String) arguments.get("from_page");
      __result.set("from_page", fromPage);
    } else {
      __result.set("from_page", "other");
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
    DiscountFragmentArgs that = (DiscountFragmentArgs) object;
    if (arguments.containsKey("from_page") != that.arguments.containsKey("from_page")) {
      return false;
    }
    if (getFromPage() != null ? !getFromPage().equals(that.getFromPage()) : that.getFromPage() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getFromPage() != null ? getFromPage().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DiscountFragmentArgs{"
        + "fromPage=" + getFromPage()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull DiscountFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DiscountFragmentArgs build() {
      DiscountFragmentArgs result = new DiscountFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setFromPage(@NonNull String fromPage) {
      if (fromPage == null) {
        throw new IllegalArgumentException("Argument \"from_page\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("from_page", fromPage);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getFromPage() {
      return (String) arguments.get("from_page");
    }
  }
}
