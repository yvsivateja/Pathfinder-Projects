package mule.learning.aggregators;

import java.util.ArrayList;
import java.util.List;

import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;

public class CustomAggregator implements AggregationStrategy {

	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		List<MuleEvent> muleEventsWithoutException = context.collectEventsWithoutExceptions();
		int muleEventsWithoutExceptionCount = muleEventsWithoutException.size();
		
		System.out.println("Mule events without exception : "+muleEventsWithoutExceptionCount);
		System.out.println("Mule events with exception : "+context.collectEventsWithExceptions().size());
		MuleEvent originalEvent = context.getOriginalEvent();
		MuleMessage message = originalEvent.getMessage();
		MuleEvent newEvent = DefaultMuleEvent.copy(originalEvent);
		List<String> paylod = new ArrayList<String>();
		for(int i=0;i<muleEventsWithoutExceptionCount;i++){
			paylod.add(muleEventsWithoutException.get(i).getMessage().getPayload(String.class));
		}
		message.setPayload(paylod);
		newEvent.setMessage(message);
		return newEvent;
	}

}
