package com.dmersiyanov.ostrovokdreamapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DreamsData {

    @SerializedName("data")
    @Expose
    private BonusData bonusData;
    @SerializedName("status")
    @Expose
    private String status;

    public BonusData getData() {
        return bonusData;
    }

    public void setData(BonusData bonusData) {
        this.bonusData = bonusData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
