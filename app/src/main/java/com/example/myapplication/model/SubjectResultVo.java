package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubjectResultVo {

    @SerializedName("sub_id")
    @Expose
    private String subId;
    @SerializedName("sub_code")
    @Expose
    private String subCode;
    @SerializedName("sub_name")
    @Expose
    private String subName;

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}


