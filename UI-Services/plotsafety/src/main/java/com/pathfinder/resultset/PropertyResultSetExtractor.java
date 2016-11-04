package com.pathfinder.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.model.PropertyPO;

@Component
public class PropertyResultSetExtractor
		implements
			ResultSetExtractor<List<PropertyPO>> {

	public List<PropertyPO> extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		List<PropertyPO> propertyPOs = new ArrayList<PropertyPO>();
		while (rs.next()) {
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
			propertyPOs.add(propertyPO);
		}
		return propertyPOs;
	}

}
