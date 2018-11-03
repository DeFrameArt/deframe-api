package com.deframe.api.gallery;

/**
 * Museum's Featured Images model
 * 
 * @author ankurbag
 *
 */
public class UpdateFeaturedImage {

	private String name;
	private String url;
	private int museumId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMuseumId() {
		return museumId;
	}

	public void setMuseumId(int museumId) {
		this.museumId = museumId;
	}

}
