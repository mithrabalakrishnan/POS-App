package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.SalesReportResponse;

import java.util.List;

public interface SalesReportContract {
    interface View extends BaseView {
        void showSuccess(SalesReportResponse saveResponse);
        void onWeaklySalesReportResponse(SalesReportResponse saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callSalesReport(String type);
        void onSalesReportApiResponse(SalesReportResponse saveResponse);

        void callWeaklySalesReport(List<String> dateList);
        void onWeaklySalesReportResponse(SalesReportResponse saveResponse);
    }
}
