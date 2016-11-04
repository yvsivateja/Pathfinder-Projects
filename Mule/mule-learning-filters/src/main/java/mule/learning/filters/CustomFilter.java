package mule.learning.filters;

import mule.learning.beans.Person;

import org.mule.api.MuleMessage;
import org.mule.api.routing.filter.Filter;

public class CustomFilter implements Filter {
	
	private Person person;

	@Override
	public boolean accept(MuleMessage message) {
		String payload = (String)message.getPayload();
		String method = message.getInboundProperty("http.method");
		return method.equalsIgnoreCase("post") && payload.equals(person.getName());
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
