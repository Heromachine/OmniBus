package com.example.jessi.omnibus.data.models;

import com.google.gson.annotations.SerializedName;

public class CouponValidation{

	@SerializedName("msg")
	private String msg;

	@SerializedName("discount")
	private String discount;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	@Override
 	public String toString(){
		return 
			"CouponValidation{" + 
			"msg = '" + msg + '\'' + 
			",discount = '" + discount + '\'' + 
			"}";
		}
}