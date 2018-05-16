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

import io.swagger.annotations.Api;

/**
 * Handles requests for the application home page.
 */
@Controller
@Api(value = "Museums", description = "Museums Controller")
@RequestMapping("/museums")
public class MuseumsController {

	private static final Logger logger = LoggerFactory.getLogger(MuseumsController.class);

	/**
	 * Get All museums
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Museum> getMusuems() {
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMusuems(connection);
	}

	/**
	 * Get Museum by Id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Museum getMusuem(@PathVariable("id") int id) {
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMusuemById(connection, id);
	}

	/**
	 * Get gallery images for the museum
	 */
	@RequestMapping(value = "/{id}/gallery", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Gallery> getMusuemGallery(@PathVariable("id") int id) {
		logger.info(this.getClass().getName() + ".getMusuemGallery: Getting the musuem gallery..");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMuseumGalleryById(connection, id);
	}

	/**
	 * Get featured images gallery images for the museum
	 */
	@RequestMapping(value = "/{id}/featuredimages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<FeaturedImages> getFeaturedImages(@PathVariable("id") int id) {
		logger.info(this.getClass().getName() + ".getFeaturedImages: Getting the musuem gallery..");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getFeaturedImagesByMuseumId(connection, id);
	}

	/**
	 * Get featured images floor plans the museum
	 */
	@RequestMapping(value = "/{id}/floorplan", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MuseumMap> getMuseumMap(@PathVariable("id") int id) {
		logger.info(this.getClass().getName() + ".getMuseumMap: Getting the musuem map..");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getMapByMuseumId(connection, id);
	}

	/**
	 * Adds a new Museum
	 * @param id
	 * @param museum
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  HttpStatus addMusuem(@RequestBody Museum museum) {
		logger.info(this.getClass().getName() + ".addMusuem: Adding Museums.");
		Connection connection = ConnectionDao.getConnection();
		return  ConnectionDao.addMuseum(connection, museum)==0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}
	
	/**
	 * Updates a Museum
	 * @param id
	 * @param museum
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public  HttpStatus updateMusuem(@PathVariable("id") int id,@RequestBody Museum museum) {
		logger.info(this.getClass().getName() + ".updateMusuem: Getting the musuem map..");
		Connection connection = ConnectionDao.getConnection();
		return  ConnectionDao.updateMuseum(connection, museum)==0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}
	
	/**
	 * Deletes a Museum
	 * @param id
	 * @param museum
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public  HttpStatus deleteMusuem(@PathVariable("id") int id) {
		logger.info(this.getClass().getName() + ".deleteMusuem: Getting the musuem map..");
		Connection connection = ConnectionDao.getConnection();
		return  ConnectionDao.deleteMuseum(connection, id)==0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}
	
	

}
