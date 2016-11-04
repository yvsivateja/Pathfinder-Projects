package com.learning.components;

import org.mule.MessageExchangePattern;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.lifecycle.Callable;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;

public class ControllableComponent implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		//Calling vm queue
		EndpointBuilder endpointBuilder = new EndpointURIEndpointBuilder(new URIBuilder("vm://tempqueue",eventContext.getMuleContext()));
		endpointBuilder.setExchangePattern(MessageExchangePattern.REQUEST_RESPONSE);
		OutboundEndpoint outboundEndpoint = endpointBuilder.buildOutboundEndpoint();
		MuleMessage muleMessage = eventContext.sendEvent(eventContext.getMessage(), outboundEndpoint);
		return muleMessage;
	}

}
