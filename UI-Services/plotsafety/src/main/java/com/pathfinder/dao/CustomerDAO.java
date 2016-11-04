package com.pathfinder.dao;

import com.pathfinder.model.CustomerPO;

public interface CustomerDAO {

	public void saveCustomerDetails(CustomerPO customerPO);

	public void updateCustomerDetails(CustomerPO customerPO);

	public void deleteCustomer(String customerID);
}
