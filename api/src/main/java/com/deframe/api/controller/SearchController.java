package com.deframe.api.controller;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deframe.api.dao.ConnectionDao;
import com.deframe.api.gallery.FeaturedImages;
import com.deframe.api.gallery.Gallery;
import com.deframe.api.gallery.MuseumMap;
import com.deframe.api.museums.Museum;
import com.deframe.api.user.User;

import io.swagger.annotations.Api;

/**
 * Handles requests for the application home page.
 */
@Controller
@Api(value = "Search-Utility", description = "Search Utility Controller")
@RequestMapping("/search")
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	/**
	 * Get Museum by name
	 */
	@RequestMapping(value = "/museums/{name}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Museum getMuseumByName(@PathVariable("name")String name) {
		logger.info(this.getClass().getName() + ".getMuseumByName: Getting museum by name.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMusuemByName(connection, name);
	}
	
	/**
	 * Get Museum by city
	 */
	@RequestMapping(value = "/city/{city}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Museum> getMuseumByCity(@PathVariable("city")String city) {
		logger.info(this.getClass().getName() + ".getMuseumByCity: Getting museum by city name.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMusuemByCity(connection, city);
	}
	
	/**
	 * Get Museum by zip
	 */
	@RequestMapping(value = "/zip/{zip}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Museum> getMusuemByZip(@PathVariable("zip")String zip) {
		logger.info(this.getClass().getName() + ".getMuseumByCity: Getting museum by zip code.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMusuemByZip(connection, zip);
	}
	
	
	/**
	 * Get Museum / Gallery by artist
	 */
	@RequestMapping(value = "/artist/{artist}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Gallery> getMuseumGalleryByArtist(@PathVariable("artist")String artist) {
		logger.info(this.getClass().getName() + ".getMuseumGalleryByArtist: Getting gallery by artist name.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMuseumGalleryByArtist(connection, artist);
	}
	
	/**
	 * Get Museum / Gallery by category
	 */
	@RequestMapping(value = "/category/{category}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Gallery> getMuseumGalleryByCategory(@PathVariable("category")String category) {
		logger.info(this.getClass().getName() + ".getMuseumGalleryByArtist: Getting gallery by category name.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMuseumGalleryByCategory(connection, category);
	}

}
