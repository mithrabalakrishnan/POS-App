package com.example.pos_android.network.api_manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleApiClient {

    private static Retrofit retrofit2;

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient okClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(7, TimeUnit.SECONDS)
                .writeTimeout(7, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .readTimeout(7, TimeUnit.SECONDS)
                .build();
    }


    public static Retrofit getClientServerApi() {

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://www.google.com")
                    .client(okClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit2;
    }


}
