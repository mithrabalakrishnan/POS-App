package com.example.pos_android.contracts;

import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.request.TableRequestData;

public interface TableReservationContract {

    interface View extends BaseView {
        void showTableOrderSuccessResponse(TableReservationResponse response);
    }

    interface Presenter extends BasePresenter {

        void doTableReservation(String date, String time, String members, String seatPreference);
        void onTableResponseCallback(TableReservationResponse tableReservationResponse);

    }
}
