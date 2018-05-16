/*package com.deframe.api.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deframe.api.request.Request;
import com.google.gson.Gson;
import com.neu.mealpass.dao.ConnectionDao;
import com.neu.mealpass.dao.MealPassConnectionDao;
import com.neu.mealpass.meal.MealPass;
import com.neu.mealpass.meal.RestaurantMeal;
import com.neu.mealpass.response.Response;
import com.neu.mealpass.response.StatusCode;
import com.neu.mealpass.user.Account;
import com.neu.mealpass.user.User;
import com.neu.mealpass.utils.MD5Hashing;

*//**
 * Handles requests for the application home page.
 *//*
@Controller
public class LogInController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	*//**
	 * Simply selects the home view to render by returning its name.
	 *//*
	@RequestMapping(value = "/login**", method = RequestMethod.POST)
	public void loginUser(HttpServletRequest request,HttpServletResponse response, @RequestBody String loginRequest){
		System.out.println("loginUser "+loginRequest);
		
		Gson gson = new Gson();
		StatusCode statusCode = StatusCode.STATUS_ERROR;
		
	    if(loginRequest !=  null){
	    	Request responseRequest = gson.fromJson(loginRequest, Request.class);
	    	if(responseRequest != null){
	    		Account account = responseRequest.getAccount();
				if(account != null){
					try {
						account.setPassword(MD5Hashing.getDecodedPassword(account.getPassword()));
						Connection connection = ConnectionDao.getConnection();
						Account account1 = ConnectionDao.loginUser(connection, account.getUserName(), account.getPassword());
						User user = ConnectionDao.getUser(connection, account.getUserName());
						MealPass mealPass = MealPassConnectionDao.getUserMealPass(connection, account.getUserName());
						RestaurantMeal restaurantMeal = MealPassConnectionDao.getUserMeal(connection, account.getUserName());
						boolean mealOrdered = false;
						if(restaurantMeal !=null){
							mealOrdered = true;
						}
						if(account1!=null){
							
						Response response2 = new Response(); 
						response2.setAccount(account1);
						response2.setUser(user);
						response2.setMealPass(mealPass);
						response2.setMealOrdered(mealOrdered);
						response2.setUserRestaurantMeal(restaurantMeal);
						statusCode = StatusCode.STATUS_OK;
						response2.setStatusCode(StatusCode.STATUS_OK);
						String json = gson.toJson(response2);
						System.out.println("loginUser Response : "+json);
						response.addHeader("Content-type", "application/json");
						response.setContentType("application/json");
						response.getWriter().write(json);
						
						}
							
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (IOException e){
						e.printStackTrace();
					}
				}else{
					statusCode = StatusCode.ACCOUNT_INVALID;
				}
	    	}
	    }
	    
	    if(!Response.isStatusOk(statusCode)){
			Response response2 = new Response(); 
			response2.setStatusCode(statusCode);
			response2.setStatusUserMessage("Error in getting meal pass options");
			String json = gson.toJson(response2);
			System.out.println("loginUser Response : "+json);
			try {
				response.addHeader("Content-type", "application/json");
				response.setContentType("application/json");
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
*/