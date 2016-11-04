package mule.learning.components;

import mule.learning.beans.Person;

import org.mule.api.annotations.param.Payload;

public class PersonLoggingComponent {
	public void logComponent(@Payload Person person){
		System.out.println(person.getName());
		System.out.println(person.getAge());
	}
}
