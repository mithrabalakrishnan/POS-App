package com.example.pos_android.contracts;

import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.data.model.UserHomeResponse;

public interface HistoryContract {

    interface View extends BaseView {
        void showResponse(HistoryResponse response);
    }

    interface Presenter extends BasePresenter {

        void getHistory();
        void onHistoryCallBack(HistoryResponse historyResponse);

    }
}
