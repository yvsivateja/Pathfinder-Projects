package com.pathfinder.director;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pathfinder.dao.PropertyDAO;
import com.pathfinder.model.CustomerPO;
import com.pathfinder.model.PropertyPO;

@Component
public class PropertyDirector {

	@Autowired
	private PropertyDAO propertyDAO;

	public List<CustomerPO> getCustomerProperties(String customerID) {
		List<CustomerPO> customerPOs = propertyDAO
				.getCustomerProperties(customerID);
		return customerPOs;
	}

	public void addProperty(PropertyPO propertyPO) {
		if (StringUtils.isEmpty(propertyPO.getPropertyID())) {
			propertyDAO.addProperty(propertyPO);
		} else {
			propertyDAO.updateProperty(propertyPO);
		}
	}

	public PropertyPO getPropertyDetails(String propertyID) {
		return propertyDAO.getProperty(propertyID);

	}

	public void deleteProperty(String propertyID) {
		propertyDAO.deleteProperty(propertyID);

	}
}
