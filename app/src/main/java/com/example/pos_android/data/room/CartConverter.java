package com.example.pos_android.data.room;

import androidx.room.TypeConverter;

import com.example.pos_android.data.model.FoodModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CartConverter {

    @TypeConverter
    public static FoodModel storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new FoodModel();
        }
        Type listType = new TypeToken<FoodModel>() {
        }.getType();
        return gson.fromJson(data, listType);
    }


    @TypeConverter
    public static String myObjectsToStoredString(FoodModel myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }

}
