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
import org.springframework.web.bind.annotation.ResponseBody;

import com.deframe.api.dao.ConnectionDao;
import com.deframe.api.user.AddUser;
import com.deframe.api.user.UpdatedUser;
import com.deframe.api.user.User;

import io.swagger.annotations.Api;

/**
 * Handles requests for the application home page.
 */
@Controller
@Api(value = "Users", description = "Users Controller")
@RequestMapping("/users")
public class UsersController {

	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	/**
	 * Get All users
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<User> getUsers() {
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getUsers(connection);
	}

	/**
	 * Get User by Id
	 */
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getUser(@PathVariable("id")int id) {
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getUserById(connection, id);
	}
	
	/**
	 * Get User by EmailAddress
	 */
	@RequestMapping(value = "/emailaddress/{email}/",method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getUser(@PathVariable("email")String email) {
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.getUserByEmailAddress(connection, email);
	}
	
	/**
	 * Adds a new User
	 * @param user
	 * @return
	 */
	@RequestMapping( value = "/add",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public  HttpStatus addUser(@RequestBody AddUser user) {
		logger.info(this.getClass().getName() + ".addMusuem: Adding User.");
		Connection connection = ConnectionDao.getConnection();
		return  ConnectionDao.addUser(connection, user)==0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}
	
	/**
	 * Updates a User
	 * @param id
	 * @param museum
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public  HttpStatus updateUser(@PathVariable("id") int id,@RequestBody UpdatedUser user) {
		logger.info(this.getClass().getName() + ".updateUser: Getting the user..");
		Connection connection = ConnectionDao.getConnection();
		return  ConnectionDao.updateUser(connection, user)==0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}
	

}
