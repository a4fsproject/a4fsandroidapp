package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentResultVo {

@SerializedName("assign_id")
@Expose
private String assignId;
@SerializedName("sub_code")
@Expose
private String subCode;
@SerializedName("issue_date")
@Expose
private String issueDate;
@SerializedName("last_date")
@Expose
private String lastDate;
@SerializedName("assign_file")
@Expose
private String assignFile;
@SerializedName("assign_details")
@Expose
private String assignDetails;
@SerializedName("sub_name")
@Expose
private String subName;
@SerializedName("s_branch")
@Expose
private String sBranch;
@SerializedName("s_semester")
@Expose
private String sSemester;

public String getAssignId() {
return assignId;
}

public void setAssignId(String assignId) {
this.assignId = assignId;
}

public String getSubCode() {
return subCode;
}

public void setSubCode(String subCode) {
this.subCode = subCode;
}

public String getIssueDate() {
return issueDate;
}

public void setIssueDate(String issueDate) {
this.issueDate = issueDate;
}

public String getLastDate() {
return lastDate;
}

public void setLastDate(String lastDate) {
this.lastDate = lastDate;
}

public String getAssignFile() {
return assignFile;
}

public void setAssignFile(String assignFile) {
this.assignFile = assignFile;
}

public String getAssignDetails() {
return assignDetails;
}

public void setAssignDetails(String assignDetails) {
this.assignDetails = assignDetails;
}

public String getSubName() {
return subName;
}

public void setSubName(String subName) {
this.subName = subName;
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

}