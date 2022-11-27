package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.BestSellingReportResponse;

public interface BestSellingReportContract {
       interface View extends BaseView {
        void showBestSellingReportResponse( BestSellingReportResponse response);
    }

    interface Presenter extends BasePresenter {

        void getBestSellingReport(String type);
        void onBestSellingReportCallback(BestSellingReportResponse response);

    }
}
