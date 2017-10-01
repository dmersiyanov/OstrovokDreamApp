package com.dmersiyanov.ostrovokdreamapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("bonus_info")
    @Expose
    private UserBonusInfo userBonusInfo;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("oauth_credentials")
    @Expose
    private OauthCredentials oauthCredentials;
    @SerializedName("is_superuser")
    @Expose
    private Boolean isSuperuser;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("future_bonus")
    @Expose
    private Integer futureBonus;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("username")
    @Expose
    private String username;

    public UserBonusInfo getUserBonusInfo() {
        return userBonusInfo;
    }

    public void setUserBonusInfo(UserBonusInfo userBonusInfo) {
        this.userBonusInfo = userBonusInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OauthCredentials getOauthCredentials() {
        return oauthCredentials;
    }

    public void setOauthCredentials(OauthCredentials oauthCredentials) {
        this.oauthCredentials = oauthCredentials;
    }

    public Boolean getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(Boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFutureBonus() {
        return futureBonus;
    }

    public void setFutureBonus(Integer futureBonus) {
        this.futureBonus = futureBonus;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}