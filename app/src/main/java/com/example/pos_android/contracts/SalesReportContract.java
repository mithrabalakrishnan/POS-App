package com.example.pos_android.contracts;

import com.example.pos_android.data.model.SalesReportResponse;

public interface SalesReportContract {
    interface View extends BaseView {
        void showSuccess(SalesReportResponse saveResponse);
        void showInputWarning();
    }
    interface Presenter extends BasePresenter {
         void callSalesReport();
        void onSalesReportApiResponse(SalesReportResponse saveResponse);
    }
}
