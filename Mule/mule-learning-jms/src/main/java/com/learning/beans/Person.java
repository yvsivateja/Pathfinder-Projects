package com.learning.beans;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 212622871686632471L;
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
