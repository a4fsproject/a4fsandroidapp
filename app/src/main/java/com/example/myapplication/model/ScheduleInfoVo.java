package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleInfoVo {

@SerializedName("result")
@Expose
private List<ScheduleResultVo> result = null;

public List<ScheduleResultVo> getResult() {
return result;
}

public void setResult(List<ScheduleResultVo> result) {
this.result = result;
}

}
