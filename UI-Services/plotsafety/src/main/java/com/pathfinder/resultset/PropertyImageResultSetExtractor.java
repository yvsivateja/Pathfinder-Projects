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

import com.pathfinder.model.PropertyImagePO;

@Component
public class PropertyImageResultSetExtractor
		implements
			ResultSetExtractor<Map<String, List<PropertyImagePO>>> {

	public Map<String, List<PropertyImagePO>> extractData(ResultSet rs)
			throws SQLException, DataAccessException {
		Map<String, List<PropertyImagePO>> propertyImagemap = new HashMap<String, List<PropertyImagePO>>();
		String prevPropertyId = "";
		List<PropertyImagePO> propertyImagePOs = null;
		while (rs.next()) {
			String currentPropertyId = rs.getString("propertyid");
			if (!prevPropertyId.contentEquals(currentPropertyId)) {
				prevPropertyId = currentPropertyId;
				propertyImagePOs = new ArrayList<PropertyImagePO>();
				PropertyImagePO propertyImagePO = preparePropertyImagePO(rs,
						currentPropertyId);
				propertyImagePOs.add(propertyImagePO);
			} else {
				PropertyImagePO propertyImagePO = preparePropertyImagePO(rs,
						currentPropertyId);
				propertyImagePOs.add(propertyImagePO);
			}
			propertyImagemap.put(currentPropertyId, propertyImagePOs);
		}
		return propertyImagemap;
	}
	private PropertyImagePO preparePropertyImagePO(ResultSet rs,
			String currentPropertyId) throws SQLException {
		PropertyImagePO propertyImagePO = new PropertyImagePO();
		propertyImagePO.setImage(rs.getBytes("image"));
		propertyImagePO.setImageName(rs.getString("imagename"));
		propertyImagePO.setPropertyID(currentPropertyId);
		propertyImagePO.setPropertyImageID(rs.getString("propertyimageid"));
		return propertyImagePO;
	}
}
