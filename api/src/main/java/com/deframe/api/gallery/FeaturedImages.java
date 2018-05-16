package com.deframe.api.gallery;

/**
 * Museum's Featured Images model
 * 
 * @author ankurbag
 *
 */
public class FeaturedImages {

	private int id;
	private String name;
	private String url;
	private int museumId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
