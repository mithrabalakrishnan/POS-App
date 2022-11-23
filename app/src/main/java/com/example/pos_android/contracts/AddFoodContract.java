package com.example.pos_android.contracts;

import com.example.pos_android.data.model.CommonResponse;
import com.example.pos_android.data.model.ImagePickerResponse;

import java.io.File;

public interface AddFoodContract {

    interface View extends BaseView {
        void showAddFoodSuccessResponse(String message);
        void showImageSuccess(ImagePickerResponse response);
    }

    interface Presenter extends BasePresenter {

        void addImageUrl(File imageUrl);
        void addFood(String imageUrl, String name, String category,String price);

        void onImageUrlResponse(ImagePickerResponse saveResponse);
        void onAddFoodResponse(CommonResponse saveResponse);

    }
}
