package com.learning.components;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Lifecycle;

import com.learning.beans.Person;

public class BootstrappedComponent implements Lifecycle, MuleContextAware{
	
	private MuleContext muleContext;
	
	
	public Person processPerson(Person person){
		System.out.println("I can access mule context :"+muleContext.getStartDate());
		person.setName("Sai Baba of Shirdi");
		return person;
	}
	@Override
	public void initialise() throws InitialisationException {
		System.out.println("initialized from bootstrap");
		
	}

	@Override
	public void start() throws MuleException {
		System.out.println("started from bootstrap");
	}

	@Override
	public void stop() throws MuleException {
		System.out.println("stopped from bootstrap");
		
	}

	@Override
	public void dispose() {
		System.out.println("disposed from bootstrap");
		
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.muleContext = context;
		
	}
}
