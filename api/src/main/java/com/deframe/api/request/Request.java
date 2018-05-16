package com.deframe.api.request;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.deframe.api.user.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2003939474974837256L;
	
	private User user;
	private HashMap<String, String> opaqueData;
	private String botMessage;
	
	public Request(){
		
	}
	
	
	public HashMap<String, String> getOpaqueData() {
		return opaqueData;
	}
	public void setOpaqueData(HashMap<String, String> opaqueData) {
		this.opaqueData = opaqueData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBotMessage() {
		return botMessage;
	}

	public void setBotMessage(String botMessage) {
		this.botMessage = botMessage;
	}
	
}
