package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Coupons{

	@SerializedName("coupons")
	private List<CouponsItem> coupons;

	public void setCoupons(List<CouponsItem> coupons){
		this.coupons = coupons;
	}

	public List<CouponsItem> getCoupons(){
		return coupons;
	}

	@Override
 	public String toString(){
		return 
			"Coupons{" + 
			"coupons = '" + coupons + '\'' + 
			"}";
		}
}