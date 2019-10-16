package com.efunhub.hotelbooking.model;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileBaseModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("profile")
    @Expose
    private List<ProfileModel> profileModelList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProfileModel> getProfileModelList() {
        return profileModelList;
    }

    public void setProfileModelList(List<ProfileModel> profileModelList) {
        this.profileModelList = profileModelList;
    }
}