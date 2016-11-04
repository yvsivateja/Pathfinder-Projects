package com.pathfinder.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerPO {
	private String customerID;
	private String identifier;
	private String name;
	private String primaryNumber;
	private String secondaryNumber;
	private String email;
	private String address;
	private String country;
	private String state;
	private String city;
	private String age;
	private String gender;
	private String additionalInfo;
	private String updatedBy;
	private List<PropertyPO> propertyPOs;
	private String totalCount;

	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public CustomerPO() {
		this.propertyPOs = new ArrayList<PropertyPO>();
	}
	public int getSize() {
		return propertyPOs.size();
	}
	public List<PropertyPO> getPropertyPOs() {
		return propertyPOs;
	}
	public void setPropertyPOs(List<PropertyPO> propertyPOs) {
		this.propertyPOs = propertyPOs;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryNumber() {
		return primaryNumber;
	}
	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}
	public String getSecondaryNumber() {
		return secondaryNumber;
	}
	public void setSecondaryNumber(String secondaryNumber) {
		this.secondaryNumber = secondaryNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
