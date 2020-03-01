package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentMarkInfoVo {

@SerializedName("result")
@Expose
private List<StudentMarkResultVo> result = null;

public List<StudentMarkResultVo> getResult() {
return result;
}

public void setResult(List<StudentMarkResultVo> result) {
this.result = result;
}

}