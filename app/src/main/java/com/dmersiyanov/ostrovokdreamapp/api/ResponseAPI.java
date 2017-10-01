package com.dmersiyanov.ostrovokdreamapp.api;

import com.dmersiyanov.ostrovokdreamapp.pojo.BonusData;
import com.dmersiyanov.ostrovokdreamapp.pojo.UserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAPI<T> {

    @SerializedName("data")
    @Expose
    private UserData userData;
    @SerializedName("error")
    @Expose
    private Object error;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data1")
    @Expose
    private BonusData bonusData;

    public BonusData getBonusData() {
        return bonusData;
    }

    public void setBonusData(BonusData bonusData) {
        this.bonusData = bonusData;
    }


    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
