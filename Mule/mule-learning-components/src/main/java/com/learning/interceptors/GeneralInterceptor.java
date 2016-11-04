package com.learning.interceptors;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.interceptor.Interceptor;
import org.mule.api.processor.MessageProcessor;

public class GeneralInterceptor implements Interceptor {

	@Override
	public void setListener(MessageProcessor listener) {
		
	}

	@Override
	public MuleEvent process(MuleEvent event) throws MuleException {
		
		MuleMessage muleMessage = event.getMessage();
		Object message = muleMessage.getPayload();
		if(message instanceof String){
			System.out.println("in here");
			muleMessage.setPayload((String)message+" sai");
		}
		return event;
	}

}
