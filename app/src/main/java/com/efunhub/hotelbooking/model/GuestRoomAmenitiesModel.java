package com.efunhub.hotelbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GuestRoomAmenitiesModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("room_id")
    @Expose
    private String roomId;
    @SerializedName("amenity_icon")
    @Expose
    private String amenityIcon;
    @SerializedName("amenity_name")
    @Expose
    private String amenityName;
    @SerializedName("r_date")
    @Expose
    private String rDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAmenityIcon() {
        return amenityIcon;
    }

    public void setAmenityIcon(String amenityIcon) {
        this.amenityIcon = amenityIcon;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getRDate() {
        return rDate;
    }

    public void setRDate(String rDate) {
        this.rDate = rDate;
    }

}