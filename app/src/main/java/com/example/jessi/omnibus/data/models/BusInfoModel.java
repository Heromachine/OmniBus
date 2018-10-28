package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BusInfoModel {

	@SerializedName("businformation")
	private List<BusinformationItem> businformation;

	public void setBusinformation(List<BusinformationItem> businformation){
		this.businformation = businformation;
	}

	public List<BusinformationItem> getBusinformation(){
		return businformation;
	}

	@Override
 	public String toString(){
		return 
			"BusInfoModel{" +
			"businformation = '" + businformation + '\'' + 
			"}";
		}
}