package com.learning;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Person {
	
	private String name;
	private String age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
