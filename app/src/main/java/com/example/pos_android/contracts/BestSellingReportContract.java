package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.BestSellingReportResponse;
import com.example.pos_android.data.model.sales_report.BestSellingReportWeeklyResponse;

import java.util.List;

public interface BestSellingReportContract {
       interface View extends BaseView {
        void showBestSellingReportResponse( BestSellingReportResponse response);
        void showBestSellingReportWeeklyResponse( BestSellingReportWeeklyResponse response);
    }

    interface Presenter extends BasePresenter {

        void getBestSellingReport(String type);
        void onBestSellingReportCallback(BestSellingReportResponse response);
        void onBestSellingReportWeeklyCallback(BestSellingReportWeeklyResponse response);
        void getBestSellingWeaklyReport(List<String> dateList);
    }
}
