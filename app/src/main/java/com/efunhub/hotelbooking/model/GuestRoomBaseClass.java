package com.efunhub.hotelbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuestRoomBaseClass {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("rooms_data")
    @Expose
    private List<GuestRoomModel> roomsData = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<GuestRoomModel> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<GuestRoomModel> roomsData) {
        this.roomsData = roomsData;
    }

}