package com.efunhub.hotelbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuestRoomModel {
    @SerializedName("room_id")
    @Expose
    private String roomId;
    @SerializedName("room_number")
    @Expose
    private String roomNumber;
    @SerializedName("room_type")
    @Expose
    private String roomType;
    @SerializedName("room_banner")
    @Expose
    private String roomBanner;
    @SerializedName("upto_guest")
    @Expose
    private String uptoGuest;
    @SerializedName("descriptions")
    @Expose
    private String descriptions;
    @SerializedName("booking_base_amount")
    @Expose
    private String bookingBaseAmount;
    @SerializedName("taxes")
    @Expose
    private String taxes;
    @SerializedName("taxes_price")
    @Expose
    private Object taxesPrice;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("discount_price")
    @Expose
    private String discountPrice;
    @SerializedName("total_payable")
    @Expose
    private Integer totalPayable;
    @SerializedName("advance_amount")
    @Expose
    private String advanceAmount;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("priority_status")
    @Expose
    private String priorityStatus;
    @SerializedName("room_status")
    @Expose
    private String roomStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("included_inprice")
    @Expose
    private String includedInprice;
    @SerializedName("all_amenities")
    @Expose
    private List<GuestRoomAmenitiesModel> allAmenities = null;
    @SerializedName("all_gallery")
    @Expose
    private List<GuestRoomGallaryModel> allGallery = null;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomBanner() {
        return roomBanner;
    }

    public void setRoomBanner(String roomBanner) {
        this.roomBanner = roomBanner;
    }

    public String getUptoGuest() {
        return uptoGuest;
    }

    public void setUptoGuest(String uptoGuest) {
        this.uptoGuest = uptoGuest;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getBookingBaseAmount() {
        return bookingBaseAmount;
    }

    public void setBookingBaseAmount(String bookingBaseAmount) {
        this.bookingBaseAmount = bookingBaseAmount;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public Object getTaxesPrice() {
        return taxesPrice;
    }

    public void setTaxesPrice(Object taxesPrice) {
        this.taxesPrice = taxesPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(Integer totalPayable) {
        this.totalPayable = totalPayable;
    }

    public String getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(String advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getPriorityStatus() {
        return priorityStatus;
    }

    public void setPriorityStatus(String priorityStatus) {
        this.priorityStatus = priorityStatus;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIncludedInprice() {
        return includedInprice;
    }

    public void setIncludedInprice(String includedInprice) {
        this.includedInprice = includedInprice;
    }

    public List<GuestRoomAmenitiesModel> getAllAmenities() {
        return allAmenities;
    }

    public void setAllAmenities(List<GuestRoomAmenitiesModel> allAmenities) {
        this.allAmenities = allAmenities;
    }

    public List<GuestRoomGallaryModel> getAllGallery() {
        return allGallery;
    }

    public void setAllGallery(List<GuestRoomGallaryModel> allGallery) {
        this.allGallery = allGallery;
    }

}