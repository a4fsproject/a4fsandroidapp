package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student_result_Vo {

    @SerializedName("enroll_number")
    @Expose
    private String enrollNumber;
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

    @SerializedName("theory_mark")
    @Expose
    private String theoryMark;

    @SerializedName("practicle_mark")
    @Expose
    private String practicleMark;

    public String getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        this.enrollNumber = enrollNumber;
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

    public String getTheoryMark() {
        return theoryMark;
    }

    public void setTheoryMark(String theoryMark) {
        this.theoryMark = theoryMark;
    }

    public String getPracticleMark() {
        return practicleMark;
    }

    public void setPracticleMark(String practicleMark) {
        this.practicleMark = practicleMark;
    }
}


