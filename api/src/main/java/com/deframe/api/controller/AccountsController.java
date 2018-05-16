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

import com.deframe.api.accounts.Account;
import com.deframe.api.dao.ConnectionDao;
import com.deframe.api.user.AddUser;
import com.deframe.api.user.UpdatedUser;
import com.deframe.api.user.User;

import io.swagger.annotations.Api;

/**
 * Handles requests for the application home page.
 */
@Controller
@Api(value = "Accounts", description = "Accounts Controller")
@RequestMapping("/accounts")
public class AccountsController {

	private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

	/**
	 * Adds a new User
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HttpStatus register(@RequestBody AddUser user) {
		logger.info(this.getClass().getName() + ".addMusuem: Adding User.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.addUser(connection, user) == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
	}

	/**
	 * Adds a new User
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HttpStatus login(@RequestBody Account account) {
		logger.info(this.getClass().getName() + ".Login user.");
		Connection connection = ConnectionDao.getConnection();
		return ConnectionDao.isAunthenticated(connection, account) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
	}
	
	/**
	 * Is EmailAddress present
	 */
	@RequestMapping(value = "/emailaddress/{email}/",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public HttpStatus isEmailAddressPresent(@PathVariable("email")String email) {
		Connection connection = ConnectionDao.getConnection();
		return (ConnectionDao.getUserByEmailAddress(connection, email) !=null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}

}
