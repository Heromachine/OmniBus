package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SeatRequestResponse{

	@SerializedName("msg")
	private List<String> msg;

	public void setMsg(List<String> msg){
		this.msg = msg;
	}

	public List<String> getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"SeatRequestResponse{" + 
			"msg = '" + msg + '\'' + 
			"}";
		}
}