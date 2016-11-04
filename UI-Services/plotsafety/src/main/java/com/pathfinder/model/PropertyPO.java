package com.pathfinder.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PropertyPO {

	private String propertyID;
	private String customerID;
	private String plotNo;
	private String surveyNo;
	private String ventureName;
	private String plotArea;
	private String facing;
	private String landmark;
	private String marketValue;
	private String marketValueMax;
	private String govtValue;
	private String transactions;
	private String developments;
	private String audit;
	private List<MultipartFile> files;
	private List<PropertyImagePO> propertyImagePOs;
	private String deletedFiles;

	public String getDeletedFiles() {
		return deletedFiles;
	}
	public void setDeletedFiles(String deletedFiles) {
		this.deletedFiles = deletedFiles;
	}
	public List<PropertyImagePO> getPropertyImagePOs() {
		return propertyImagePOs;
	}
	public void setPropertyImagePOs(List<PropertyImagePO> propertyImagePOs) {
		this.propertyImagePOs = propertyImagePOs;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}
	public String getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(String surveyNo) {
		this.surveyNo = surveyNo;
	}
	public String getVentureName() {
		return ventureName;
	}
	public void setVentureName(String ventureName) {
		this.ventureName = ventureName;
	}
	public String getPlotArea() {
		return plotArea;
	}
	public void setPlotArea(String plotArea) {
		this.plotArea = plotArea;
	}
	public String getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = facing;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getMarketValueMax() {
		return marketValueMax;
	}
	public void setMarketValueMax(String marketValueMax) {
		this.marketValueMax = marketValueMax;
	}
	public String getGovtValue() {
		return govtValue;
	}
	public void setGovtValue(String govtValue) {
		this.govtValue = govtValue;
	}
	public String getTransactions() {
		return transactions;
	}
	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}
	public String getDevelopments() {
		return developments;
	}
	public void setDevelopments(String developments) {
		this.developments = developments;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}

}
