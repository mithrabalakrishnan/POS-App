package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.TableReservationContract;
import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.request.TableRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

public class TableReservationPresenter implements TableReservationContract.Presenter {

    TableReservationContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;

    public TableReservationPresenter(TableReservationContract.View mView, Context context) {
        mApiDataManager = new ApiDataManager();
        mContext = context;
        this.mView = mView;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void onApiError(String data) {
        mView.hideProgressBar();
        mView.showApiErrorWarning(data);
    }

    @Override
    public void doTableReservation(String date, String time, String members, String seatPreference) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            mApiDataManager.makeTableReservation(new TableRequestData(
                            date, time, members, seatPreference
                    ),
                    sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onTableResponseCallback(TableReservationResponse tableReservationResponse) {
        mView.hideProgressBar();
        if (tableReservationResponse.getStatus()) {
            mView.showTableOrderSuccessResponse(tableReservationResponse);
        } else
            mView.showWarningMessage(tableReservationResponse.getMessage());
    }
}
