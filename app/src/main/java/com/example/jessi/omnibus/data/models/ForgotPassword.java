package com.example.jessi.omnibus.data.models;

import com.google.gson.annotations.SerializedName;

public class ForgotPassword{

	@SerializedName("msg")
	private String msg;

	@SerializedName("password")
	private String password;

	@SerializedName("mobile")
	private String mobile;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	@Override
 	public String toString(){
		return 
			"ForgotPassword{" + 
			"msg = '" + msg + '\'' + 
			",password = '" + password + '\'' + 
			",mobile = '" + mobile + '\'' + 
			"}";
		}
}