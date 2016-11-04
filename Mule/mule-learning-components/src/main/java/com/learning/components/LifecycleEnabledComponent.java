package com.learning.components;

import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Lifecycle;

import com.learning.beans.Person;

public class LifecycleEnabledComponent implements Lifecycle {
	
	
	@Override
	public void initialise() throws InitialisationException {
		System.out.println("Initialized lifecycle");
	}

	@Override
	public void start() throws MuleException {
		System.out.println("Started lifecycle");
	}

	@Override
	public void stop() throws MuleException {
		System.out.println("Stopped lifecycle");
	}

	@Override
	public void dispose() {
		System.out.println("Disposed lifecycle");
	}
	
	public void doSomething(Person p){
		System.out.println("Name : "+p.getName());
	}

}

