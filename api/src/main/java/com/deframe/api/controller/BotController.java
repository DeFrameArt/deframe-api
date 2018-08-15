package com.deframe.api.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deframe.api.dao.ConnectionDao;
import com.deframe.api.museums.Museum;
import com.deframe.api.response.BotResponse;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import io.swagger.annotations.Api;

@Controller
@Api(value = "DeFrameBot", description = "Bot Controller")
@RequestMapping("/deframe-bot")
public class BotController {

	public static String API_AI_TOKEN = "2b4cf0614aed45d28422a28ceaa439e9";
	public static String ASK_PRICE = "What is the price to visit the (ICA, MFA, Isabella) museum?";
	private AIConfiguration configuration;
	private AIDataService dataService;
	
	BotController(){
		configuration = new AIConfiguration(API_AI_TOKEN);
		dataService = new AIDataService(configuration);
	}
	
	/**
	 * gets museum object
	 * @param params
	 * @param aiResponse
	 * @param input
	 * @param museum
	 * @return
	 */
	private Museum getMuseum(HashMap params, AIResponse aiResponse, String input, Museum museum){
		params = aiResponse.getResult().getParameters();
		input = params.get("Museum") != null ? params.get("Museum").toString() : "";
		input = input.replace("\"", "");
		
		museum = ConnectionDao.getMusuemByName(ConnectionDao.getConnection(), input);
		if (museum == null)
			museum = ConnectionDao.getMusuemByAcronym(ConnectionDao.getConnection(), input);
		
		return museum;
	}

	/**
	 * Invokes BOT.
	 * @param textinput
	 * @return
	 */
	@RequestMapping(value = "/{textinput}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public BotResponse askBot(@PathVariable("textinput") String textinput, HttpServletRequest req) {
		BotResponse botresponse = new BotResponse();
		////////TODO fix for the session management		
		/*
		HashMap params = new HashMap();
		HttpSession session = req.getSession();
		String sid = "";
		String tsid = "";
		*/
		try {
			
			//configuration = new AIConfiguration(API_AI_TOKEN);
			//dataService = new AIDataService(configuration);
			
			AIRequest request = new AIRequest(textinput);
			
			AIResponse aiResponse =  dataService.request(request);
			////////TODO fix for the session management
			/*
			if(session.getAttribute("tsessid")!=null){
				sid = (String) session.getAttribute("sessid");
				tsid = (String) session.getAttribute("tsessid");
			}
			else{
				session.setAttribute("sessid",req.getSession().getId());
				session.setAttribute("tsessid",req.getSession().getId()+request.getSessionId());
			}
			
			System.out.println(" iddd-- "+tsid + " " + (String) session.getAttribute("tsessid"));
		
			//System.out.println(req.getSession().getId() + " " +request.getSessionId());
			 
			 */
			if (aiResponse.getStatus().getCode() == 200) {
				botresponse.setSpeech(aiResponse.getResult().getFulfillment().getSpeech());
				// TODO: MVP +
				//String intentName = "";
				//String aiResponseText = "";
				//String input = "";
				
				//if (aiResponse.getResult() != null && aiResponse.getResult().getMetadata() != null &&
					//aiResponse.getResult().getMetadata().getIntentName() != null)
					//intentName = aiResponse.getResult().getMetadata().getIntentName();

				//Museum museum = null;

				//switch (intentName) {
				/*TODO in MVP Plus
				case "GetMuseumLocation":
					museum = getMuseum(params, aiResponse, input, museum);
					aiResponseText = "The museum " + input + " is located at " + museum.getStreet() + ", "
							+ museum.getCity() + ", " + museum.getState() + " " + museum.getZip();
					botresponse.setSpeech(aiResponseText);
					break;
				case "GetPrice":
					museum = getMuseum(params, aiResponse, input, museum);
					museum = ConnectionDao.getMuseumPrice(ConnectionDao.getConnection(), museum.getId());
					botresponse.setSpeech(museum.getPriceDetails());
					break;
				case "GetVisitingHours":
					museum = getMuseum(params, aiResponse, input, museum);
					museum = ConnectionDao.getMuseumTimings(ConnectionDao.getConnection(), museum.getId());
					botresponse.setSpeech(museum.getTimingDetails());
					break;
					*/
				//default:
					//botresponse.setSpeech(aiResponse.getResult().getFulfillment().getSpeech());
					//break;
				//}
			} else {
				botresponse.setSpeech(aiResponse.getStatus().getErrorDetails());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return botresponse;
	}

}
