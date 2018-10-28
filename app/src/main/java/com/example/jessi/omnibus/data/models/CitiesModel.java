package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CitiesModel {

	@SerializedName("city")
	private List<CityItem> city;

	public void setCity(List<CityItem> city){
		this.city = city;
	}

	public List<CityItem> getCity(){
		return city;
	}

	@Override
 	public String toString(){
		return 
			"CitiesModel{" +
			"city = '" + city + '\'' + 
			"}";
		}
}