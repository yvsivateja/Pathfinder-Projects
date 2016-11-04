package com.pathfinder.model;

public class Customer {
	private String uniqueID;
	private String customerID;
	private String customerName;
	private String primaryNumber;
	private String secondaryNumber;
	private String emailID;
	private PlotDetails plotDetails;
	private CustomerDetails customerDetails;

	public Customer() {
		this.plotDetails = new PlotDetails();
		this.customerDetails = new CustomerDetails();
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public PlotDetails getPlotDetails() {
		return plotDetails;
	}

	public void setPlotDetails(PlotDetails plotDetails) {
		this.plotDetails = plotDetails;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getPrimaryNumber() {
		return primaryNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
