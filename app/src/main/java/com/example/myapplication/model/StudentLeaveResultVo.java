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
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("s_mob_number")
    @Expose
    private String sMobNumber;
    @SerializedName("s_password")
    @Expose
    private String sPassword;
    @SerializedName("s_address")
    @Expose
    private String sAddress;
    @SerializedName("s_dob")
    @Expose
    private String sDob;
    @SerializedName("s_email_id")
    @Expose
    private String sEmailId;
    @SerializedName("s_branch")
    @Expose
    private String sBranch;
    @SerializedName("s_semester")
    @Expose
    private String sSemester;
    @SerializedName("s_photo")
    @Expose
    private String sPhoto;

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

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSMobNumber() {
        return sMobNumber;
    }

    public void setSMobNumber(String sMobNumber) {
        this.sMobNumber = sMobNumber;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getSDob() {
        return sDob;
    }

    public void setSDob(String sDob) {
        this.sDob = sDob;
    }

    public String getSEmailId() {
        return sEmailId;
    }

    public void setSEmailId(String sEmailId) {
        this.sEmailId = sEmailId;
    }

    public String getSBranch() {
        return sBranch;
    }

    public void setSBranch(String sBranch) {
        this.sBranch = sBranch;
    }

    public String getSSemester() {
        return sSemester;
    }

    public void setSSemester(String sSemester) {
        this.sSemester = sSemester;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }

}