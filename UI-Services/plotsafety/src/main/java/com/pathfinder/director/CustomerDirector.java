package com.pathfinder.director;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pathfinder.dao.CustomerDAO;
import com.pathfinder.dao.PropertyDAO;
import com.pathfinder.model.CustomerPO;

@Component
public class CustomerDirector {

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private PropertyDAO propertyDAO;

	public void saveCustomerDetails(CustomerPO customerPO) {
		if (StringUtils.isEmpty(customerPO.getCustomerID())) {
			customerDAO.saveCustomerDetails(customerPO);
		} else {
			customerDAO.updateCustomerDetails(customerPO);
		}
	}

	public List<CustomerPO> viewCustomers() {
		return propertyDAO.getAllCustomerDetails();
	}

	public List<CustomerPO> getCustomerDetails(String customerID) {
		return propertyDAO.getCustomerProperties(customerID);
	}

	public void deleteCustomer(String customerID) {
		customerDAO.deleteCustomer(customerID);
	}
}
