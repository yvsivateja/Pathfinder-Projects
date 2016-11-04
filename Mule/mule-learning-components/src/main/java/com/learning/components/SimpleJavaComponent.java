package com.learning.components;

import com.learning.beans.Address;
import com.learning.beans.Person;

public class SimpleJavaComponent {
	
	private Address address;
	
	public SimpleJavaComponent(){
		System.out.println("SimpleJavaComponent instantiated");
	}

	public void doSomething(Person person){
		System.out.println("Name : "+person.getName()+" Age : "+person.getAge());
		System.out.println("Street : "+address.getStreet()+" City : "+address.getCity());
	}
	
	public void doThat(){
		System.out.println("No argument is called");
	}
	
	public void printThat(String message){
		System.out.println("Hello : "+message);
	}
	

	public void setAddress(Address address) {
		this.address = address;
	}
}
