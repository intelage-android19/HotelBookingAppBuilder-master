package com.efunhub.hotelbooking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletHistoryBaseModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("wallat_history")
    @Expose
    private List<WalletHistorymodel> wallatHistory = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<WalletHistorymodel> getWallatHistory() {
        return wallatHistory;
    }

    public void setWallatHistory(List<WalletHistorymodel> wallatHistory) {
        this.wallatHistory = wallatHistory;
    }

}
