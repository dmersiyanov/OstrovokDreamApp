package com.dmersiyanov.ostrovokdreamapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BonusLog {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("delta")
    @Expose
    private Integer delta;
    @SerializedName("order_item_data")
    @Expose
    private OrderItemData orderItemData;
    @SerializedName("reason")
    @Expose
    private String reason;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }

    public OrderItemData getOrderItemData() {
        return orderItemData;
    }

    public void setOrderItemData(OrderItemData orderItemData) {
        this.orderItemData = orderItemData;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
