package com.deframe.api.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6822947642627063837L;
	
	public SignupRequest(){
		
	}
	
	
}
