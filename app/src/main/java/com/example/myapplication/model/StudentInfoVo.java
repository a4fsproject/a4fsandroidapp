package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentInfoVo {

@SerializedName("result")
@Expose
private List<Student_result_Vo> result = null;

public List<Student_result_Vo> getResult() {
return result;
}

public void setResult(List<Student_result_Vo> result) {
this.result = result;
}



}