package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleResultVo {

    @SerializedName("schedule_id")
    @Expose
    private String scheduleId;
    @SerializedName("schedule_type")
    @Expose
    private String scheduleType;
    @SerializedName("schedule_file")
    @Expose
    private String scheduleFile;
    @SerializedName("schedule_note")
    @Expose
    private String scheduleNote;
    @SerializedName("s_semester")
    @Expose
    private String sSemester;
    @SerializedName("s_branch")
    @Expose
    private String sBranch;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleFile() {
        return scheduleFile;
    }

    public void setScheduleFile(String scheduleFile) {
        this.scheduleFile = scheduleFile;
    }

    public String getScheduleNote() {
        return scheduleNote;
    }

    public void setScheduleNote(String scheduleNote) {
        this.scheduleNote = scheduleNote;
    }

    public String getSSemester() {
        return sSemester;
    }

    public void setSSemester(String sSemester) {
        this.sSemester = sSemester;
    }

    public String getSBranch() {
        return sBranch;
    }

    public void setSBranch(String sBranch) {
        this.sBranch = sBranch;
    }

}
