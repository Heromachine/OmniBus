package com.example.jessi.omnibus.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Routes{

	@SerializedName("route")
	private List<RouteItem> route;

	public void setRoute(List<RouteItem> route){
		this.route = route;
	}

	public List<RouteItem> getRoute(){
		return route;
	}

	@Override
 	public String toString(){
		return 
			"Routes{" + 
			"route = '" + route + '\'' + 
			"}";
		}
}