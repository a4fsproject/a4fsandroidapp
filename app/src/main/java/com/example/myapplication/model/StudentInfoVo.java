package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInfoVo {

@SerializedName("result")
@Expose
private List<StudentResultVo> result = null;

public List<StudentResultVo> getResult() {
return result;
}

public void setResult(List<StudentResultVo> result) {
this.result = result;
}



}