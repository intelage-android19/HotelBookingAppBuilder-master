package com.efunhub.hotelbooking.model;

public class HomeFragmentModel {


    public int imgLocation;
    public String txtLocationName,id;
    public HomeFragmentModel(String id, int img, String location) {

        this.id = id;
        this.imgLocation = img;
        this.txtLocationName = location;


    }

    public String getTxtxLocationName() {
        return txtLocationName;
    }

    public String getId() {
        return id;
    }

    public int getImgHome() {
        return imgLocation;
    }

}
