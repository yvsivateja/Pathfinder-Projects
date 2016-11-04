package mule.learning.beans;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 6729886851568847912L;
	
	private String state;
	private String city;
	private String zipCode;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
