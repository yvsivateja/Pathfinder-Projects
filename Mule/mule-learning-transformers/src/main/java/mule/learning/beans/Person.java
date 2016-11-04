package mule.learning.beans;

import java.io.Serializable;


public class Person implements Serializable{
	
	private static final long serialVersionUID = 2433546327937687018L;
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
