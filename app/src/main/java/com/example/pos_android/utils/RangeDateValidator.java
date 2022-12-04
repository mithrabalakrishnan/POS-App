package com.example.pos_android.utils;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.concurrent.TimeUnit;

@SuppressLint("ParcelCreator")
public class RangeDateValidator implements CalendarConstraints.DateValidator {

    final int numberOfDays;
    private MaterialDatePicker rangePicker;

    public RangeDateValidator(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setDatePicker(MaterialDatePicker rangePicker) {
        this.rangePicker = rangePicker;
    }

    @Override
    public boolean isValid(long date) {
        Pair<Long, Long> selection = (Pair<Long, Long>) rangePicker.getSelection();
        if (selection != null) {
            Long startDate = selection.first;
            if (startDate != null) {
                long days = (numberOfDays - 1) * TimeUnit.DAYS.toMillis(1);
                ;
                if (date > startDate + days)
                    return false;
                if (date < startDate)
                    return false;
            }
        }
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }
}