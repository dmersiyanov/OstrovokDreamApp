package com.dmersiyanov.ostrovokdreamapp.pojo;

/**
 * Created by Mitya on 19.09.2017.
 */

public class Dreams extends BonusLog {
    private int amount;
    private String reason;
    private String date;

    public Dreams(int amount, String reason, String date) {
        this.amount = amount;
        this.reason = reason;
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
