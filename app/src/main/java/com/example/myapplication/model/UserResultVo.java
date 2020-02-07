package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResultVo {

    @SerializedName("l_id")
    @Expose
    private String lId;
    @SerializedName("l_reason")
    @Expose
    private String lReason;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("l_status")
    @Expose
    private String lStatus;
    @SerializedName("s_enroll_number")
    @Expose
    private String sEnrollNumber;
    @SerializedName("u_from_date")
    @Expose
    private String uFromDate;
    @SerializedName("u_to_date")
    @Expose
    private String uToDate;
    @SerializedName("ul_photo")
    @Expose
    private String ulPhoto;
    @SerializedName("total_days")
    @Expose
    private String totalDays;

    public String getLId() {
        return lId;
    }

    public void setLId(String lId) {
        this.lId = lId;
    }

    public String getLReason() {
        return lReason;
    }

    public void setLReason(String lReason) {
        this.lReason = lReason;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getLStatus() {
        return lStatus;
    }

    public void setLStatus(String lStatus) {
        this.lStatus = lStatus;
    }

    public String getSEnrollNumber() {
        return sEnrollNumber;
    }

    public void setSEnrollNumber(String sEnrollNumber) {
        this.sEnrollNumber = sEnrollNumber;
    }

    public String getUFromDate() {
        return uFromDate;
    }

    public void setUFromDate(String uFromDate) {
        this.uFromDate = uFromDate;
    }

    public String getUToDate() {
        return uToDate;
    }

    public void setUToDate(String uToDate) {
        this.uToDate = uToDate;
    }

    public String getUlPhoto() {
        return ulPhoto;
    }

    public void setUlPhoto(String ulPhoto) {
        this.ulPhoto = ulPhoto;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

}