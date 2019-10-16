package com.efunhub.hotelbooking.model;

public class RoomListModel {

    public int id, imgRoomList;
    public String txtRoomType,txtRoomSize,txtRoomPeople,txtRoomLunch;


    public RoomListModel(int id, int imgRoomList, String txtRoomType, String txtRoomSize, String txtRoomLunch, String txtRoomPeople) {

        this.id = id;
        this.imgRoomList = imgRoomList;
        this.txtRoomType = txtRoomType;
        this.txtRoomSize = txtRoomSize;
        this.txtRoomLunch = txtRoomLunch;
        this.txtRoomPeople = txtRoomPeople;



    }


    public int getId() {
        return id;
    }

    public int getImgRoomList() {
        return imgRoomList;
    }

    public String getTxtRoomType() {
        return txtRoomType;
    }

    public String getTxtRoomSize() {
        return txtRoomSize;
    }

    public String getTxtRoomPeople() {
        return txtRoomPeople;
    }

    public String getTxtRoomLunch() {
        return txtRoomLunch;
    }

}
