package com.learning.beans;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 6729886851568847912L;
	
	private String street;
	private String city;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
