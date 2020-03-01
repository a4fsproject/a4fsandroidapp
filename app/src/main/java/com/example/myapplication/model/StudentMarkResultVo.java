package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentMarkResultVo {

@SerializedName("m_id")
@Expose
private String mId;
@SerializedName("sub_code")
@Expose
private String subCode;
@SerializedName("s_branch")
@Expose
private String sBranch;
@SerializedName("s_semester")
@Expose
private String sSemester;
@SerializedName("sub_name")
@Expose
private String subName;
@SerializedName("mark_data")
@Expose
private String markData;

public String getMId() {
return mId;
}

public void setMId(String mId) {
this.mId = mId;
}

public String getSubCode() {
return subCode;
}

public void setSubCode(String subCode) {
this.subCode = subCode;
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

public String getSubName() {
return subName;
}

public void setSubName(String subName) {
this.subName = subName;
}

public String getMarkData() {
return markData;
}

public void setMarkData(String markData) {
this.markData = markData;
}

}