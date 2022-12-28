package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.SalesReportResponse;

public interface ForecastingContract {
    interface View extends BaseView {

        void onForecastingResponse(SalesReportResponse saveResponse);
    }

    interface Presenter extends BasePresenter {
        void getForecastingReport();

        void onForecastingApiResponse(SalesReportResponse saveResponse);
    }
}
