package com.efunhub.hotelbooking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GuestRoomGallaryModel implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("room_id")
    @Expose
    private String roomId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("r_date")
    @Expose
    private String rDate;

  /*  public GuestRoomGallaryModel(Parcel in) {

                 id = in.readString();
                 roomId = in.readString();
                 image = in.readString();


    }*/

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRDate() {
        return rDate;
    }

    public void setRDate(String rDate) {
        this.rDate = rDate;
    }

   /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeString(id);
            dest.writeString(roomId);
            dest.writeString(image);

        }

        public static final Parcelable.Creator<GuestRoomGallaryModel> CREATOR = new Parcelable.Creator<GuestRoomGallaryModel>() {
            public GuestRoomGallaryModel createFromParcel(Parcel in) {
                return new GuestRoomGallaryModel(in);
            }

            public GuestRoomGallaryModel[] newArray(int size) {
                return new GuestRoomGallaryModel[size];
            }
        };*/

    }
