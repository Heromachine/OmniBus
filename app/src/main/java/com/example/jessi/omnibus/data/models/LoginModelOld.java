package com.example.jessi.omnibus.data.models;

import com.google.gson.annotations.SerializedName;

public class LoginModelOld {

	@SerializedName("msg")
	private String msg;

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("appapikey ")
	private String appapikey;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("userid")
	private String userid;

	@SerializedName("email")
	private String email;

	@SerializedName("lastname")
	private String lastname;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAppapikey(String appapikey){
		this.appapikey = appapikey;
	}

	public String getAppapikey(){
		return appapikey;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"LoginModelOld{" +
			"msg = '" + msg + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",appapikey  = '" + appapikey + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",userid = '" + userid + '\'' + 
			",email = '" + email + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}