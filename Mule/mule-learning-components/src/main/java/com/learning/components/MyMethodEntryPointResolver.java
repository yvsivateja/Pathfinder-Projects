package com.learning.components;

import com.learning.beans.Address;
import com.learning.beans.Person;

public class MyMethodEntryPointResolver {
	public void printPerson(Person person){
		System.out.println("Name : "+person.getName()+" Age : "+person.getAge());
	}
	public void changePerson(Person person){
		person.setName("Rahul");
	}
	
	public void logAddress(Address address){
		System.out.println("Street : "+address.getStreet()+" City : "+address.getCity());
	}
	
	public void log(String message){
		System.out.println(message);
	}
}
