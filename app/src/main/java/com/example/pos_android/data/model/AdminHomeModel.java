package com.example.pos_android.data.model;

public class AdminHomeModel {
    private String name;
    private int imageId;
    private String discription;

     // Constructor
    public AdminHomeModel(String name, int imageId, String discription){
        this.name = name;
        this.imageId = imageId;
        this.discription = discription;
    }

    //Setting up the getter methods
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getdiscription() {
        return discription;
    }
}
