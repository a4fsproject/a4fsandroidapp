package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentLeaveResultVo {

    @SerializedName("sl_id")
    @Expose
    private String slId;
    @SerializedName("s_enroll_number")
    @Expose
    private String sEnrollNumber;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("status")
    @Expose
    private String status;

    public String getSlId() {
        return slId;
    }

    public void setSlId(String slId) {
        this.slId = slId;
    }

    public String getSEnrollNumber() {
        return sEnrollNumber;
    }

    public void setSEnrollNumber(String sEnrollNumber) {
        this.sEnrollNumber = sEnrollNumber;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}