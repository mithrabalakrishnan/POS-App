package com.example.pos_android.contracts;

import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;

import java.util.List;

public interface IncomePerItemMonthlyContract {
            interface View extends BaseView {
        void showIncomePerItemMonthlyResponse( IncomePerItemMonthlyResponse response);
    }

    interface Presenter extends BasePresenter {

        void geIncomePerItemMonthly(int id);
        void geIncomePerItemWeakly(int id, List<String> dateList);
        void onIncomePerItemMonthlyCallback(IncomePerItemMonthlyResponse response);

    }
}
