package com.learning.services;

import org.mule.api.MuleMessage;
/**
 * These return types of these methods must match with the response payload type of corresponding endpoint calls.
 * Generically, we can return MuleMessage too, in which case we don't bother about the response type.
 * @author Arun Rahul
 *
 */
public interface AppenderService {

	//Payload is expected to be string
	public String callVMService(String payload);
	
	//No payload is send to the corresponding binding
	public MuleMessage callHttpService();
}
