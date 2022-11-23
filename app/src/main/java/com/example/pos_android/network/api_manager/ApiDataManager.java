package com.example.pos_android.network.api_manager;

import android.util.Log;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.data.model.ImagePickerResponse;
import com.example.pos_android.data.model.LoginResponse;
import com.example.pos_android.data.model.RegisterResponse;
import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.request.AddFoodRequestData;
import com.example.pos_android.data.model.request.FoodOrderRequestData;
import com.example.pos_android.data.model.request.LoginRequestData;
import com.example.pos_android.data.model.request.RegisterRequestData;
import com.example.pos_android.data.model.request.TableRequestData;
import com.example.pos_android.presenter.AddFoodPresenter;
import com.example.pos_android.presenter.ConfirmOrderPresenter;
import com.example.pos_android.presenter.HistoryPresenter;
import com.example.pos_android.presenter.LoginPresenter;
import com.example.pos_android.presenter.RegisterPresenter;
import com.example.pos_android.presenter.TableReservationPresenter;
import com.example.pos_android.presenter.UserHomePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class ApiDataManager {
    private final String TAG = "OnNetworkResponse";
    ApiInterFace apiInterFace;

    public void loginUser(LoginRequestData loginRequestData, LoginPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .login(loginRequestData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            mPresenter.onApiResponse(loginResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void registerUser(RegisterRequestData requestData, RegisterPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .registerUser(requestData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<RegisterResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(RegisterResponse response) {
                            mPresenter.onApiResponse(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void getImageUrlFromImage(MultipartBody.Part image, String token, AddFoodPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .getImageUrl("Bearer " + token, image)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ImagePickerResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(ImagePickerResponse response) {
                            mPresenter.onImageUrlResponse(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            // mPresenter.onApiError(e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void addFood(AddFoodRequestData requestData, String token, AddFoodPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .updateFood("Bearer " + token, requestData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<CommonResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(CommonResponse response) {
                            mPresenter.onAddFoodResponse(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void getUserHomeDetails(String token, UserHomePresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .userHomeDetails("Bearer " + token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<UserHomeResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(UserHomeResponse response) {
                            mPresenter.onHomeResponseCallback(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void makeTableReservation(TableRequestData requestData, String token, TableReservationPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .doReserveTable("Bearer " + token, requestData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<TableReservationResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(TableReservationResponse response) {
                            mPresenter.onTableResponseCallback(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void makeFoodOrder(FoodOrderRequestData requestData, String token, ConfirmOrderPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .doOrderFood("Bearer " + token, requestData)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<CommonResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(CommonResponse response) {
                            mPresenter.onAddFoodResponseCallback(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }

    public void getUserHistory(String token, HistoryPresenter mPresenter) {
        try {
            if (apiInterFace == null)
                apiInterFace = ApiClient.getClientServerApi().create(ApiInterFace.class);

            apiInterFace
                    .userHistory("Bearer " + token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<HistoryResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(HistoryResponse response) {
                            mPresenter.onHistoryCallBack(response);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            mPresenter.onApiError(e.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });


        } catch (Exception e) {
            mPresenter.onApiError(e.getMessage());
            Log.e(TAG, "Exception caught in " + e.getMessage().toString());
        }
    }


}