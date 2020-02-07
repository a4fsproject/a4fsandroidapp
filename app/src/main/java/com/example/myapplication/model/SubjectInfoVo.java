package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubjectInfoVo {

@SerializedName("result")
@Expose
private List<SubjectResultVo> result = null;

public List<SubjectResultVo> getResult() {
return result;
}

public void setResult(List<SubjectResultVo> result) {
this.result = result;
}



}