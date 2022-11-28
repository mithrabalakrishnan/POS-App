package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;

public interface IncomePerItemMonthlyContract {
            interface View extends BaseView {
        void showIncomePerItemMonthlyResponse( IncomePerItemMonthlyResponse response);
    }

    interface Presenter extends BasePresenter {

        void geIncomePerItemMonthly(int id);
        void onIncomePerItemMonthlyCallback(IncomePerItemMonthlyResponse response);

    }
}
