package com.pathfinder.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pathfinder.model.CustomerPO;
import com.pathfinder.model.PropertyImagePO;
import com.pathfinder.model.PropertyPO;
import com.pathfinder.resultset.CustomerPropertyResultSetExtractor;
import com.pathfinder.resultset.PropertyImageResultSetExtractor;
import com.pathfinder.resultset.PropertyResultSetExtractor;

@Component
@Transactional
public class PropertyDAOImpl implements PropertyDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private CustomerPropertyResultSetExtractor customerPropertyResultSetExtractor;

	@Autowired
	private PropertyImageResultSetExtractor propertyImageResultSetExtractor;

	@Autowired
	private PropertyResultSetExtractor propertyResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public List<CustomerPO> getCustomerProperties(String customerID) {
		String customerPropertiesSQL = "SELECT customer.customerid,customer.customeridentifier,customer.name,customer.contact1,customer.contact2,customer.email,"
				+ "customer.address,customer.country,customer.state,customer.city,customer.age,customer.gender,customer.additionalinfo,"
				+ "customer.createdtimestamp,customer.createdby,customer.updatetimestamp,customer.updatedby,property.propertyid,property.customerid,"
				+ "property.plotno,property.surveyno,property.venturename,property.plotarea,property.facing,property.landmark,property.marketvalue,"
				+ "property.govtvalue,property.transactions,property.developments,property.audit "
				+ "FROM customer LEFT JOIN property on customer.customerid=property.customerid WHERE customer.customerid like :cid";
		Map<String, String> bind = new HashMap<String, String>(1);
		bind.put("cid", customerID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		List<CustomerPO> customerPOs = namedParameterJdbcTemplate.query(
				customerPropertiesSQL, paramSource,
				customerPropertyResultSetExtractor);
		if (!customerID.contentEquals("%") && customerPOs != null
				&& customerPOs.size() > 0) {
			String propertyImageSQL = "SELECT propertyimageid,propertyid,image,imagename FROM property_image "
					+ "WHERE propertyid IN (select propertyid from customer where customerid=:cid) "
					+ "order by propertyid";
			bind = new HashMap<String, String>(1);
			bind.put("cid", customerID);
			paramSource = new MapSqlParameterSource(bind);

			Map<String, List<PropertyImagePO>> propertyImagemap = namedParameterJdbcTemplate
					.query(propertyImageSQL, paramSource,
							propertyImageResultSetExtractor);
			for (PropertyPO propertyPO : customerPOs.get(0).getPropertyPOs()) {
				List<PropertyImagePO> propertyImagePOs = propertyImagemap
						.get(propertyPO.getPropertyID());
				propertyPO.setPropertyImagePOs(propertyImagePOs);
			}

		}
		return customerPOs;

	}

	public List<CustomerPO> getAllCustomerDetails() {
		String allCustomersSQL = "SELECT customer.customerid,customer.customeridentifier,customer.name,customer.contact1,customer.contact2,customer.email,"
				+ "customer.address,customer.country,customer.state,customer.city,customer.age,customer.gender,customer.additionalinfo,"
				+ "customer.createdtimestamp,customer.createdby,customer.updatetimestamp,customer.updatedby,c.totalCount "
				+ "FROM customer LEFT JOIN (Select property.customerid,count(*) totalCount "
				+ "FROM property group by property.customerid) c on "
				+ "c.customerid = customer.customerid";
		List<CustomerPO> customerPOs = namedParameterJdbcTemplate.query(
				allCustomersSQL, customerPropertyResultSetExtractor);
		return customerPOs;

	}

	public void addProperty(PropertyPO propertyPO) {
		String insertPropertySQL = "INSERT INTO property (customerid,plotno,surveyno,venturename,"
				+ "plotarea,facing,landmark,marketvalue,govtvalue,transactions,developments,audit,marketvaluemax) VALUES "
				+ "(:customerid,:plotno,:surveyno,:venturename,:plotarea,:facing,:landmark,:marketvalue,"
				+ ":govtvalue,:transactions,:developments,:audit,:marketvaluemax)";
		validateAndSetDefaultValues(propertyPO);
		Map<String, Object> bind = new HashMap<String, Object>(12);
		bind.put("customerid", propertyPO.getCustomerID());
		bind.put("plotno", propertyPO.getPlotNo());
		bind.put("surveyno", propertyPO.getSurveyNo());
		bind.put("venturename", propertyPO.getVentureName());
		bind.put("plotarea", propertyPO.getPlotArea());
		bind.put("facing", propertyPO.getFacing());
		bind.put("landmark", propertyPO.getLandmark());
		bind.put("marketvalue", propertyPO.getMarketValue());
		bind.put("govtvalue", propertyPO.getGovtValue());
		bind.put("transactions", propertyPO.getTransactions());
		bind.put("developments", propertyPO.getDevelopments());
		if (!StringUtils.isEmpty(propertyPO.getAudit())) {
			bind.put("audit", 1);
		} else {
			bind.put("audit", null);
		}
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = {"propertyid"};
		int propertyInserted = namedParameterJdbcTemplate.update(
				insertPropertySQL, paramSource, keyHolder, keyColumnName);

		if (propertyInserted > 0) {
			propertyPO.setPropertyID(keyHolder.getKey().toString());
			insertPropertyImages(propertyPO);
		}

	}

	private void validateAndSetDefaultValues(PropertyPO propertyPO) {
		if (StringUtils.isEmpty(propertyPO.getMarketValue())) {
			propertyPO.setMarketValue(null);
		} else {
			String marketValue = propertyPO.getMarketValue();
			propertyPO.setMarketValue(marketValue.split("-")[0]);
			propertyPO.setMarketValueMax(marketValue.split("-")[1]);
		}
		if (StringUtils.isEmpty(propertyPO.getGovtValue())) {
			propertyPO.setGovtValue(null);
		}
	}

	private void insertPropertyImages(PropertyPO propertyPO) {
		String insertImageProperty = "INSERT INTO property_image(propertyid,image,imagename) VALUES (:propertyid,:image,:imagename)";
		try {
			for (MultipartFile multipartFile : propertyPO.getFiles()) {
				if (multipartFile != null
						&& !StringUtils.isEmpty(multipartFile
								.getOriginalFilename())) {
					System.out.println(multipartFile.getOriginalFilename());
					Map<String, Object> bind = new HashMap<String, Object>(3);
					bind.put("propertyid", propertyPO.getPropertyID());
					Blob blob = new SerialBlob(multipartFile.getBytes());
					bind.put("image", blob);
					bind.put("imagename", multipartFile.getOriginalFilename());
					SqlParameterSource paramSource = new MapSqlParameterSource(
							bind);
					namedParameterJdbcTemplate.update(insertImageProperty,
							paramSource);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PropertyPO getProperty(String propertyID) {
		String propertySQL = "Select propertyid,customerid,plotno,surveyno,venturename,plotarea,facing,landmark,"
				+ "marketvalue,govtvalue,transactions,developments,audit "
				+ "FROM property WHERE propertyid=:propertyid";
		Map<String, Object> bind = new HashMap<String, Object>(3);
		bind.put("propertyid", propertyID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		List<PropertyPO> propertyPOs = namedParameterJdbcTemplate.query(
				propertySQL, paramSource, propertyResultSetExtractor);
		if (propertyPOs != null && propertyPOs.size() > 0) {
			String propertyImageSQL = "SELECT propertyimageid,propertyid,image,imagename FROM property_image "
					+ "WHERE propertyid=:propertyid";
			Map<String, List<PropertyImagePO>> propertyImagePOs = namedParameterJdbcTemplate
					.query(propertyImageSQL, paramSource,
							propertyImageResultSetExtractor);
			propertyPOs.get(0).setPropertyImagePOs(
					propertyImagePOs.get(propertyID));
			return propertyPOs.get(0);
		}
		return null;
	}

	public void updateProperty(PropertyPO propertyPO) {
		String updatePropertySQL = "UPDATE property SET plotno = :plotno,surveyno = :surveyno,"
				+ "venturename = :venturename,plotarea = :plotarea,facing = :facing,"
				+ "landmark = :landmark,marketvalue = :marketvalue,govtvalue = :govtvalue,"
				+ "transactions = :transactions,developments = :developments,audit = :audit "
				+ "WHERE propertyid = :propertyid";
		Map<String, Object> bind = new HashMap<String, Object>(12);
		bind.put("propertyid", propertyPO.getPropertyID());
		bind.put("plotno", propertyPO.getPlotNo());
		bind.put("surveyno", propertyPO.getSurveyNo());
		bind.put("venturename", propertyPO.getVentureName());
		bind.put("plotarea", propertyPO.getPlotArea());
		bind.put("facing", propertyPO.getFacing());
		bind.put("landmark", propertyPO.getLandmark());
		bind.put("marketvalue", propertyPO.getMarketValue());
		bind.put("govtvalue", propertyPO.getGovtValue());
		bind.put("transactions", propertyPO.getTransactions());
		bind.put("developments", propertyPO.getDevelopments());
		if (!StringUtils.isEmpty(propertyPO.getAudit())) {
			bind.put("audit", 1);
		} else {
			bind.put("audit", null);
		}
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(updatePropertySQL, paramSource);
		System.out.println(propertyPO.getDeletedFiles());
		if (!StringUtils.isEmpty(propertyPO.getDeletedFiles())) {
			String[] propertyImagesID = propertyPO.getDeletedFiles().split(",");
			for (String id : propertyImagesID) {
				deletePropertyImages(id);
			}
		}

		insertPropertyImages(propertyPO);
	}

	public void deleteProperty(String propertyID) {
		String deletePropertySQL = "DELETE FROM property WHERE propertyid= :propertyid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("propertyid", propertyID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(deletePropertySQL, paramSource);
	}

	private void deletePropertyImages(String propertyImageID) {
		String deletePropertyImageSQL = "DELETE FROM property_image WHERE propertyimageid IN (:propertyimageid)";
		Map<String, Object> bind = new HashMap<String, Object>(12);
		bind.put("propertyimageid", propertyImageID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(deletePropertyImageSQL, paramSource);

	}
}
