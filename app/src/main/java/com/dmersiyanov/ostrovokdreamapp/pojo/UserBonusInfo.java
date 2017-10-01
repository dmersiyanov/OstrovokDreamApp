package com.dmersiyanov.ostrovokdreamapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserBonusInfo {

    @SerializedName("is_disabled_by_user")
    @Expose
    private Boolean isDisabledByUser;
    @SerializedName("is_spendable")
    @Expose
    private Boolean isSpendable;
    @SerializedName("is_disabled_by_admin")
    @Expose
    private Boolean isDisabledByAdmin;
    @SerializedName("points")
    @Expose
    private Integer points;

    public Boolean getIsDisabledByUser() {
        return isDisabledByUser;
    }

    public void setIsDisabledByUser(Boolean isDisabledByUser) {
        this.isDisabledByUser = isDisabledByUser;
    }

    public Boolean getIsSpendable() {
        return isSpendable;
    }

    public void setIsSpendable(Boolean isSpendable) {
        this.isSpendable = isSpendable;
    }

    public Boolean getIsDisabledByAdmin() {
        return isDisabledByAdmin;
    }

    public void setIsDisabledByAdmin(Boolean isDisabledByAdmin) {
        this.isDisabledByAdmin = isDisabledByAdmin;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}