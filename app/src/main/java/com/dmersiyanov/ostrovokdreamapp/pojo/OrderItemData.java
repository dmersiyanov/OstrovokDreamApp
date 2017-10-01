package com.dmersiyanov.ostrovokdreamapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemData {

    @SerializedName("hotel_name")
    @Expose
    private String hotelName;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
