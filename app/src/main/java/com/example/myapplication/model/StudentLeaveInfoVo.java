package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentLeaveInfoVo {

@SerializedName("result")
@Expose
private List<StudentLeaveResultVo> result = null;

public List<StudentLeaveResultVo> getResult() {
return result;
}

public void setResult(List<StudentLeaveResultVo> result) {
this.result = result;
}

}