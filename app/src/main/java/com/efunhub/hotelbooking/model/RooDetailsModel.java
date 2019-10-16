package com.efunhub.hotelbooking.model;

public class RooDetailsModel {
    public int imgamenities,id;
    public String txtamenities;

        public RooDetailsModel(int id, int imgamenities, String txtamenities) {

            this.id = id;
            this.imgamenities = imgamenities;
            this.txtamenities = txtamenities;


        }

        public String getTxtamenities() {
            return txtamenities;
        }

        public int getId() {
            return id;
        }

        public int getImgamenities() {
            return imgamenities;
        }


}
