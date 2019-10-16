package com.efunhub.hotelbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AmenitiesBaseModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("allAmenities")
    @Expose
    private List<AmenitiesModel> allAmenities = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AmenitiesModel> getAllAmenities() {
        return allAmenities;
    }

    public void setAllAmenities(List<AmenitiesModel> allAmenities) {
        this.allAmenities = allAmenities;
    }

}
