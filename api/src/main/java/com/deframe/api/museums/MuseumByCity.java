package com.deframe.api.museums;

import java.util.List;

public class MuseumByCity {
	String city;
	List<Museum> museums;
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the museums
	 */
	public List<Museum> getMuseums() {
		return museums;
	}
	/**
	 * @param museums the museums to set
	 */
	public void setMuseums(List<Museum> museums) {
		this.museums = museums;
	}
	

}
