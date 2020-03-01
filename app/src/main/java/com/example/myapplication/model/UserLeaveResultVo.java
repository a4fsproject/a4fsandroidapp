package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLeaveResultVo {

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
    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("u_mob_number")
    @Expose
    private String uMobNumber;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("u_address")
    @Expose
    private String uAddress;
    @SerializedName("u_designation")
    @Expose
    private String uDesignation;
    @SerializedName("u_dob")
    @Expose
    private String uDob;
    @SerializedName("u_email_id")
    @Expose
    private String uEmailId;
    @SerializedName("u_photo")
    @Expose
    private String uPhoto;

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

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUMobNumber() {
        return uMobNumber;
    }

    public void setUMobNumber(String uMobNumber) {
        this.uMobNumber = uMobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getUDesignation() {
        return uDesignation;
    }

    public void setUDesignation(String uDesignation) {
        this.uDesignation = uDesignation;
    }

    public String getUDob() {
        return uDob;
    }

    public void setUDob(String uDob) {
        this.uDob = uDob;
    }

    public String getUEmailId() {
        return uEmailId;
    }

    public void setUEmailId(String uEmailId) {
        this.uEmailId = uEmailId;
    }

    public String getUPhoto() {
        return uPhoto;
    }

    public void setUPhoto(String uPhoto) {
        this.uPhoto = uPhoto;
    }
}