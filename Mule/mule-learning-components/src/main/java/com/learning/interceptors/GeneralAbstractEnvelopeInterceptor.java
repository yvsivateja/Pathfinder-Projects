package com.learning.interceptors;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.interceptor.AbstractEnvelopeInterceptor;
import org.mule.management.stats.ProcessingTime;

public class GeneralAbstractEnvelopeInterceptor extends AbstractEnvelopeInterceptor{

	@Override
	public MuleEvent before(MuleEvent event) throws MuleException {
		System.out.println("before");
		appender(event);
		return event;
	}

	@Override
	public MuleEvent after(MuleEvent event) throws MuleException {
		System.out.println("after");
		appender(event);
		return event;
	}

	@Override
	public MuleEvent last(MuleEvent event, ProcessingTime time, long startTime,
			boolean exceptionWasThrown) throws MuleException {
		return event;
	}
	
	private void appender(MuleEvent event){
		MuleMessage muleMessage = event.getMessage();
		Object message = muleMessage.getPayload();
		System.out.println(message.getClass().getCanonicalName());
		if(message instanceof String){
			System.out.println("in here general");
			muleMessage.setPayload((String)message+" sai");
		}
	}

}
