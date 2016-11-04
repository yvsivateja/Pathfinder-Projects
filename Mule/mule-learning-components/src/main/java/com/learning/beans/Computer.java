package com.learning.beans;

import javax.inject.Inject;

import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Lifecycle;

public class Computer implements Lifecycle {
	
	@Override
	public void initialise() throws InitialisationException {
		System.out.println("Initialized");
	}

	@Override
	public void start() throws MuleException {
		System.out.println("Started");
	}

	@Override
	public void stop() throws MuleException {
		System.out.println("Stopped");
	}

	@Override
	public void dispose() {
		System.out.println("Disposed");
	}
	
	@Inject
	private Address address;
		
	public void beginComputer(){
		System.out.println("Beginning computer");
		System.out.println("Street : "+address.getStreet());
	}
}
