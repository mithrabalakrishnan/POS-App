package com.example.pos_android.data.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.room.CartConverter;

@Entity(tableName = "cart")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    public int orderId;

    @ColumnInfo(name = "user_id")
    public String userId;

    @TypeConverters(CartConverter.class)
    //@ColumnInfo(name = "food")
    public FoodModel food;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "quantity")
    public int quantity;


}
