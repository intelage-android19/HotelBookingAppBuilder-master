package com.efunhub.hotelbooking.model;

public class RoomGalaryModel {

    public int id;
    public int imgGallery;


    public RoomGalaryModel(int id, int imgGallery) {

        this.id = id;
        this.imgGallery = imgGallery;
    }


    public int getId() {
        return id;
    }

    public int getImgGallery() {
        return imgGallery;
    }

}
