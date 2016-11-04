package com.learning.components;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.learning.beans.Person;

public class MyCallableComponent implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage muleMessage = eventContext.getMessage();
		Person person = muleMessage.getPayload(Person.class);
		person.setName("Rahul");
		return person;
	}

}
