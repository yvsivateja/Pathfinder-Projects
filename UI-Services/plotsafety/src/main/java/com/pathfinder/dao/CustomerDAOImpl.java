package com.pathfinder.dao;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.model.CustomerPO;

@Component
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	private static final String IDENTIFIER_PREFIX = "PSCUST-";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void saveCustomerDetails(CustomerPO customerPO) {
		String customerInsertSQL = "INSERT INTO customer(name,contact1,contact2,email,address,country,state,city,age,gender,additionalinfo,"
				+ "createdby,updatedby) VALUES (:name,:contact1,:contact2,:email,:address,:country,:state,:city,:age,:gender,"
				+ ":additionalinfo,:createdby,:updatedby)";
		String[] keyColumnName = {"customerid"};
		Map<String, Object> bind = new HashMap<String, Object>(13);
		bind.put("name", customerPO.getName());
		bind.put("contact1", customerPO.getPrimaryNumber());
		bind.put("contact2", customerPO.getSecondaryNumber());
		bind.put("email", customerPO.getEmail());
		bind.put("address", customerPO.getAddress());
		bind.put("country", customerPO.getCountry());
		bind.put("state", customerPO.getState());
		bind.put("city", customerPO.getCity());
		bind.put("age", customerPO.getAge());
		bind.put("gender", customerPO.getGender());
		bind.put("additionalinfo", customerPO.getAdditionalInfo());
		bind.put("createdby", customerPO.getUpdatedBy());
		bind.put("updatedby", customerPO.getUpdatedBy());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int customersInserted = namedParameterJdbcTemplate.update(
				customerInsertSQL, paramSource, keyHolder, keyColumnName);

		if (customersInserted > 0) {
			customerPO.setCustomerID(keyHolder.getKey().toString());
			String updateCustomerIdentifierSQL = "UPDATE customer SET customeridentifier=:customeridentifier WHERE customerid=:customerid";
			String generatedID = IDENTIFIER_PREFIX
					+ getGeneratedID(customerPO.getCustomerID());
			bind = new HashMap<String, Object>(2);
			bind.put("customeridentifier", generatedID);
			bind.put("customerid", customerPO.getCustomerID());
			paramSource = new MapSqlParameterSource(bind);

			namedParameterJdbcTemplate.update(updateCustomerIdentifierSQL,
					paramSource);

		}

	}

	public void deleteCustomer(String customerID) {
		String deleteCustomerSQL = "DELETE FROM customer WHERE customerid= :customerid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("customerid", customerID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(deleteCustomerSQL, paramSource);
	}

	private String getGeneratedID(String value) {
		String pattern = "00000";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(Integer.parseInt(value));
		return output;
	}

	public void updateCustomerDetails(CustomerPO customerPO) {
		String updateCustomerSQL = "UPDATE customer SET name = :name,contact1 = :contact1, "
				+ "contact2 = :contact2, email = :email, address = :address, country = :country, "
				+ "state = :state, city = :city ,age = :age, gender = :gender, "
				+ "additionalinfo = :additionalinfo, updatetimestamp = :updatetimestamp, updatedby = :updatedby "
				+ "WHERE customerid = :customerid";

		Map<String, Object> bind = new HashMap<String, Object>(14);
		bind.put("name", customerPO.getName());
		bind.put("contact1", customerPO.getPrimaryNumber());
		bind.put("contact2", customerPO.getSecondaryNumber());
		bind.put("email", customerPO.getEmail());
		bind.put("address", customerPO.getAddress());
		bind.put("country", customerPO.getCountry());
		bind.put("state", customerPO.getState());
		bind.put("city", customerPO.getCity());
		bind.put("age", customerPO.getAge());
		bind.put("gender", customerPO.getGender());
		bind.put("additionalinfo", customerPO.getAdditionalInfo());
		bind.put("updatedby", customerPO.getUpdatedBy());
		bind.put("updatetimestamp", new Date());
		bind.put("customerid", customerPO.getCustomerID());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(updateCustomerSQL, paramSource);
	}

}
