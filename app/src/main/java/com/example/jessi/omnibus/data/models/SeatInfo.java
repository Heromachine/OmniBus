package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SeatInfo{

	@SerializedName("seatinformation")
	private List<SeatinformationItem> seatinformation;

	public void setSeatinformation(List<SeatinformationItem> seatinformation){
		this.seatinformation = seatinformation;
	}

	public List<SeatinformationItem> getSeatinformation(){
		return seatinformation;
	}

	@Override
 	public String toString(){
		return 
			"SeatInfo{" + 
			"seatinformation = '" + seatinformation + '\'' + 
			"}";
		}
}