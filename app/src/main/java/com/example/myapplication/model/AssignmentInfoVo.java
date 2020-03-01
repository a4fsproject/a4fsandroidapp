package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentInfoVo {

@SerializedName("result")
@Expose
private List<AssignmentResultVo> result = null;

public List<AssignmentResultVo> getResult() {
return result;
}

public void setResult(List<AssignmentResultVo> result) {
this.result = result;
}

}