package com.deframe.api.museums;

public class Museum {
	int id;
	String name;
	String city;
	String street;
	String state;
	String country;
	String zip;
	float lat;
	float lng;
	String bannerUrl;
	String logoUrl;
	String priceDetails;
	String timingDetails;
	String Acronym;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getPriceDetails() {
		return priceDetails;
	}
	public void setPriceDetails(String priceDetails) {
		this.priceDetails = priceDetails;
	}
	public String getTimingDetails() {
		return timingDetails;
	}
	public void setTimingDetails(String timingDetails) {
		this.timingDetails = timingDetails;
	}
	/**
	 * @return the acronym
	 */
	public String getAcronym() {
		return Acronym;
	}
	/**
	 * @param acronym the acronym to set
	 */
	public void setAcronym(String acronym) {
		Acronym = acronym;
	}

}
