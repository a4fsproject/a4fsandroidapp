package com.example.myapplication.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserleaveInfoVo {

@SerializedName("result")
@Expose
private List<UserResultVo> result = null;

public List<UserResultVo> getResult() {
return result;
}

public void setResult(List<UserResultVo> result) {
this.result = result;
}

}