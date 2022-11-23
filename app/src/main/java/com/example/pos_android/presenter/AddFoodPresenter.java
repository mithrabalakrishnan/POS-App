package com.example.pos_android.presenter;

import android.content.Context;

import com.example.pos_android.R;
import com.example.pos_android.contracts.AddFoodContract;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.ImagePickerResponse;
import com.example.pos_android.data.model.request.AddFoodRequestData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.network.api_manager.ApiDataManager;
import com.example.pos_android.utils.NetworkManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddFoodPresenter implements AddFoodContract.Presenter {

    AddFoodContract.View mView;
    ApiDataManager mApiDataManager;
    Context mContext;
    SessionManager sessionManager;


    public AddFoodPresenter(AddFoodContract.View mView, Context context) {
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
    public void addImageUrl(File file) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            mApiDataManager.getImageUrlFromImage(multipartBody, sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void addFood(String imageUrl, String name, String category, String price) {
        if (NetworkManager.isNetworkAvailable(mContext)) {
            mView.showProgressBar();
            AddFoodRequestData requestData = new AddFoodRequestData(
                    name, category, price, imageUrl
            );

            mApiDataManager.addFood(requestData, sessionManager.getUserToken(), this);

        } else
            mView.showWarningMessage(mContext.getString(R.string.no_network));
    }

    @Override
    public void onImageUrlResponse(ImagePickerResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showImageSuccess(saveResponse);
        }
    }

    @Override
    public void onAddFoodResponse(CommonResponse saveResponse) {
        mView.hideProgressBar();
        if (saveResponse.getStatus()) {
            mView.showAddFoodSuccessResponse(saveResponse.getMessage());
        }
    }
}
