package com.pathfinder.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.model.CustomerPO;
import com.pathfinder.model.PropertyPO;

@Component
public class CustomerPropertyResultSetExtractor
		implements
			ResultSetExtractor<List<CustomerPO>> {

	public List<CustomerPO> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		Map<String, CustomerPO> customerMap = new HashMap<String, CustomerPO>();
		String prevCustomerID = "";
		CustomerPO customerPO = null;
		List<PropertyPO> propertyPOs = null;
		while (rs.next()) {

			if (!prevCustomerID.contentEquals(rs.getString("customerid"))) {

				prevCustomerID = rs.getString("customerid");
				propertyPOs = new ArrayList<PropertyPO>();
				customerPO = prepareCustomerPO(rs);
				if (checkElementExists(rs, "propertyid")
						&& rs.getString("propertyid") != null) {
					propertyPOs = new ArrayList<PropertyPO>();
					PropertyPO propertyPO = preparePropertyPO(rs);
					propertyPOs.add(propertyPO);
				}
				customerPO.setPropertyPOs(propertyPOs);
			} else {
				PropertyPO propertyPO = preparePropertyPO(rs);
				propertyPOs.add(propertyPO);
				customerPO.setPropertyPOs(propertyPOs);
			}
			customerMap.put(rs.getString("customerid"), customerPO);
		}
		List<CustomerPO> customerPOs = new ArrayList<CustomerPO>(
				customerMap.values());
		return customerPOs;
	}
	private boolean checkElementExists(ResultSet rs, String columnName) {
		try {
			rs.getString(columnName);
		} catch (java.sql.SQLException e) {
			return false;
		}
		return true;
	}
	private PropertyPO preparePropertyPO(ResultSet rs) throws SQLException {
		PropertyPO propertyPO = new PropertyPO();
		propertyPO.setAudit(rs.getString("audit"));
		propertyPO.setCustomerID(rs.getString("customerid"));
		propertyPO.setDevelopments(rs.getString("developments"));
		propertyPO.setFacing(rs.getString("facing"));
		propertyPO.setGovtValue(rs.getString("govtvalue"));
		propertyPO.setLandmark(rs.getString("landmark"));
		propertyPO.setMarketValue(rs.getString("marketvalue"));
		propertyPO.setPlotArea(rs.getString("plotarea"));
		propertyPO.setPlotNo(rs.getString("plotno"));
		propertyPO.setPropertyID(rs.getString("propertyid"));
		propertyPO.setSurveyNo(rs.getString("surveyno"));
		propertyPO.setTransactions(rs.getString("transactions"));
		propertyPO.setVentureName(rs.getString("venturename"));
		return propertyPO;
	}

	private CustomerPO prepareCustomerPO(ResultSet rs) throws SQLException {
		CustomerPO customerPO;
		customerPO = new CustomerPO();
		customerPO.setAdditionalInfo(rs.getString("additionalinfo"));
		customerPO.setAddress(rs.getString("address"));
		customerPO.setAge(rs.getString("age"));
		customerPO.setCity(rs.getString("city"));
		customerPO.setCountry(rs.getString("country"));
		customerPO.setCustomerID(rs.getString("customerid"));
		customerPO.setEmail(rs.getString("email"));
		customerPO.setGender(rs.getString("gender"));
		customerPO.setName(rs.getString("name"));
		customerPO.setPrimaryNumber(rs.getString("contact1"));
		customerPO.setSecondaryNumber(rs.getString("contact2"));
		customerPO.setState(rs.getString("state"));
		customerPO.setUpdatedBy(rs.getString("updatedby"));
		customerPO.setIdentifier(rs.getString("customeridentifier"));
		if (checkElementExists(rs, "totalCount")) {
			customerPO.setTotalCount(rs.getString("totalCount"));
		}
		return customerPO;
	}
}
