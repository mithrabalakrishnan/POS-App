package com.example.pos_android.contracts;

import com.example.pos_android.data.model.AddKitchenResponse;
import com.example.pos_android.data.model.CustomerReportResponse;

public interface CustomerReportContract {

    interface View extends BaseView {
        void showReportResponse(CustomerReportResponse response);
    }

    interface Presenter extends BasePresenter {
        void getReport(String month);
        void onReportResponse(CustomerReportResponse response);

    }
}
