package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLeaveInfoVo {

@SerializedName("result")
@Expose
private List<UserLeaveResultVo> result = null;

public List<UserLeaveResultVo> getResult() {
return result;
}

public void setResult(List<UserLeaveResultVo> result) {
this.result = result;
}

}