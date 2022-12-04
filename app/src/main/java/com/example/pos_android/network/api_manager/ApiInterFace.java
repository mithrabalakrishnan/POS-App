package com.example.pos_android.network.api_manager;

import com.example.pos_android.data.model.AddKitchenResponse;
import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.data.model.ImagePickerResponse;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.data.model.KitchenUpdateStatusResponse;
import com.example.pos_android.data.model.LoginResponse;
import com.example.pos_android.data.model.RegisterResponse;
import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.UserHomeResponse;
import com.example.pos_android.data.model.UserProfileResponse;
import com.example.pos_android.data.model.kitchen.KitchenOrderResponse;
import com.example.pos_android.data.model.kitchen.KitchenUpdateOrderPayload;
import com.example.pos_android.data.model.request.AddFoodRequestData;
import com.example.pos_android.data.model.request.FoodOrderRequestData;
import com.example.pos_android.data.model.request.KitchenRequestData;
import com.example.pos_android.data.model.request.LoginRequestData;
import com.example.pos_android.data.model.request.RegisterRequestData;
import com.example.pos_android.data.model.request.TableRequestData;
import com.example.pos_android.data.model.sales_report.BestSellingReportResponse;
import com.example.pos_android.data.model.sales_report.IncomePerItemMonthlyResponse;
import com.example.pos_android.data.model.sales_report.SalesReportResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterFace {

    @POST("register")
    Observable<RegisterResponse> registerUser(
            @Body RegisterRequestData requestData);

    @POST("login")
    Observable<LoginResponse> login(
            @Body LoginRequestData requestData);

    @Multipart
    @POST("upload-file")
    Observable<ImagePickerResponse> getImageUrl(
            @Header("Authorization") String string,
            @Part MultipartBody.Part imageFile);

    @POST("add-menu")
    Observable<CommonResponse> updateFood(
            @Header("Authorization") String string,
            @Body AddFoodRequestData requestData);

    @GET("user-home")
    Observable<UserHomeResponse> userHomeDetails(
            @Header("Authorization") String string
    );

    @POST("table-booking")
    Observable<TableReservationResponse> doReserveTable(
            @Header("Authorization") String string,
            @Body TableRequestData requestData);

    @POST("food-order")
    Observable<CommonResponse> doOrderFood(
            @Header("Authorization") String string,
            @Body FoodOrderRequestData requestData);

    @GET("user-history")
    Observable<HistoryResponse> userHistory(
            @Header("Authorization") String string
    );

    @GET("report-monthly")
    Observable<SalesReportResponse> salesReportMonthly(
            @Header("Authorization") String string
    );
    @GET("report-weekly")
    Observable<SalesReportResponse> getReportWeekly(
            @Header("Authorization") String string,
               @Query("dateList") List<String> dateList
    );
    @GET("report-food-sales-monthly")
    Observable<BestSellingReportResponse> bestSellingReport(
            @Header("Authorization") String string,
            @Query("type") String type
    );

    @GET("report-food-sales-weekly")
    Observable<BestSellingReportResponse> reportFoodSalesWeekly(
            @Header("Authorization") String string,
            @Query("dateList") List<String> dateList
    );

    @GET("report-food-item-monthly")
    Observable<IncomePerItemMonthlyResponse> incomePerItemMonthly(
            @Header("Authorization") String string,
            @Query("foodId") int foodId
    );

    @GET("report-food-item-weekly")
    Observable<IncomePerItemMonthlyResponse> reportFoodItemWeekly(
            @Header("Authorization") String string,
            @Query("foodId") int foodId,
            @Query("dateList") List<String> dateList
    );

    @GET("kitchen-order")
    Observable<KitchenResponse> kitchenGetOrders(
            @Header("Authorization") String string
    );

    @PUT("kitchen-order")
    Observable<KitchenUpdateStatusResponse> kitchenUpdateOrder(
            @Header("Authorization") String string,
            @Body KitchenRequestData requestData);

    @GET("user-profile")
    Observable<UserProfileResponse> userGetProfile(
            @Header("Authorization") String string
    );

    @POST("kitchen-register")
    Observable<AddKitchenResponse> addKitchenUser(
            @Header("Authorization") String string,
            @Body RegisterRequestData requestData);

}
