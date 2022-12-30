package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.SalesReportResponse;
import com.example.pos_android.data.model.sales_report.forecasting.forcasting_response;

public interface ForecastingContract {
    interface View extends BaseView {

        void onForecastingResponse(forcasting_response saveResponse);
    }

    interface Presenter extends BasePresenter {
        void getForecastingReport();

        void onForecastingApiResponse(forcasting_response saveResponse);
    }
}
