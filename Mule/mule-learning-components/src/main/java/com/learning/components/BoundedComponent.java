package com.learning.components;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.learning.beans.Person;
import com.learning.services.AppenderService;

/**
 * To use component bindings, this class need not implement Callable.
 * Just for the sake of simplicity, we are using this.
 * @author Arun Rahul
 *
 */
public class BoundedComponent implements Callable {

	private AppenderService appenderService;
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage muleMessage = eventContext.getMessage();
		String payload = muleMessage.getPayload(String.class);
		appenderService.callVMService(payload);
		return appenderService.callHttpService();
	}
	
	public AppenderService getAppenderService() {
		return appenderService;
	}
	
	public void setAppenderService(AppenderService appenderService) {
		this.appenderService = appenderService;
	}

}
