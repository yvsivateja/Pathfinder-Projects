package com.pathfinder.dao;

import java.util.List;

import com.pathfinder.model.CustomerPO;
import com.pathfinder.model.PropertyPO;

public interface PropertyDAO {

	public List<CustomerPO> getCustomerProperties(String customerID);

	public void addProperty(PropertyPO propertyPO);

	public PropertyPO getProperty(String propertyID);

	public void updateProperty(PropertyPO propertyPO);

	public List<CustomerPO> getAllCustomerDetails();

	public void deleteProperty(String propertyID);

}
