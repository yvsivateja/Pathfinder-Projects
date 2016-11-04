package com.pathfinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.GroupRegistrationDTO;
import com.pathfinder.dto.GroupsResultDTO;
import com.pathfinder.resultsetextractors.GroupResultSetExtractor;

@Component
@Transactional
public class GroupDaoImpl implements GroupDao {

	@Autowired
	GroupResultSetExtractor groupResultSetExtractor;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void saveGroup(GroupRegistrationDTO groupRegistrationDTO) {

		String groupInsertSQL = "INSERT INTO company (companyname,shortkey, address1,address2,address3,city,country,state,notes) VALUES (:companyname,:shortkey, :address1,:address2,:address3,:city,:country,:state,:notes)";

		Map<String, Object> bind = new HashMap<String, Object>(8);
		bind.put("companyname", groupRegistrationDTO.getGroupName());
		bind.put("shortkey", groupRegistrationDTO.getShortKey());
		bind.put("address1", groupRegistrationDTO.getAddress1());
		bind.put("address2", groupRegistrationDTO.getAddress2());
		bind.put("address3", groupRegistrationDTO.getAddress3());
		bind.put("city", groupRegistrationDTO.getCity());
		bind.put("country", groupRegistrationDTO.getCountry());
		bind.put("state", groupRegistrationDTO.getState());
		bind.put("notes", groupRegistrationDTO.getAdditionalInfo());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "companyid" };

		int groupsInserted = namedParameterJdbcTemplate.update(groupInsertSQL,
				paramSource, keyHolder, keyColumnName);

		if (groupsInserted > 0) {

			String groupId = keyHolder.getKey().toString();
			groupRegistrationDTO.setGroupid(groupId);
			String employeeInsertSQL = "INSERT INTO employee (employeename, phone,emailid,age,sex,companyid) VALUES (:employeename, :phone,:emailId,:age,:sex,:companyid)";
			String[] employeekeyColumnName = { "employee_id" };
			bind = new HashMap<String, Object>(6);
			bind.put("employeename", groupRegistrationDTO.getEmpName());
			bind.put("phone", groupRegistrationDTO.getEmpName());
			bind.put("emailId", groupRegistrationDTO.getEmpEmailid());
			bind.put("age", groupRegistrationDTO.getAge());
			bind.put("sex", groupRegistrationDTO.getSex());
			bind.put("companyid", groupId);

			paramSource = new MapSqlParameterSource(bind);
			keyHolder = new GeneratedKeyHolder();

			int groupEmployeesInserted = namedParameterJdbcTemplate.update(
					employeeInsertSQL, paramSource, keyHolder,
					employeekeyColumnName);

			if (groupEmployeesInserted > 0) {
				String groupEmployeeId = keyHolder.getKey().toString();
				groupRegistrationDTO.setGroupEmployeeId(groupEmployeeId);
				String roleId = insertDefaultRole(groupId);
				insertUserWithRole(groupEmployeeId, roleId,
						groupRegistrationDTO.getUsername(), groupId);
			}

		}

	}

	private String insertDefaultRole(String companyId) {
		String roleInsertSQL = "INSERT INTO roles (rolename, status,isdefault,companyid) VALUES (:rolename, :status,:isdefault,:companyid)";

		Map<String, Object> bind = new HashMap<String, Object>(4);
		bind.put("rolename", "Company Admin");
		bind.put("status", true);
		bind.put("isdefault", false);
		bind.put("companyid", companyId);

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "roleid" };

		int rolesInserted = namedParameterJdbcTemplate.update(roleInsertSQL,
				paramSource, keyHolder, keyColumnName);
		String roleId = "";
		if (rolesInserted > 0) {
			roleId = keyHolder.getKey().toString();
			String roleACLInsertSQL = "INSERT INTO rolesacl (roleid, cancreate,canupdate,candelete,canview,module) VALUES (:roleid, :cancreate,:canupdate,:candelete,:canview,:module)";
			List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();
			for (String moduleName : RealConstants.MODULES) {
				if (!moduleName.contentEquals(RealConstants.MODULENAME.COMPANY
						.name())) {
					bind = new HashMap<String, Object>(5);
					bind.put("roleid", roleId);
					bind.put("cancreate", true);
					bind.put("canupdate", true);
					bind.put("candelete", true);
					bind.put("canview", true);
					bind.put("module", moduleName);
					paramSourceList.add(new MapSqlParameterSource(bind));
				}
			}
			int[] roleACLInserted = namedParameterJdbcTemplate.batchUpdate(
					roleACLInsertSQL,
					paramSourceList.toArray(new SqlParameterSource[0]));
			System.out.println("Modules Inserted : " + roleACLInserted.length);

		}
		return roleId;
	}

	public void insertUserWithRole(String employeeId, String roleId,
			String userName, String companyId) {

		String userInsertSQL = "INSERT INTO users (username, password,status,companyid,employeeid,issuperadmin) VALUES (:username, :password,:status,:companyid,:employeeid,:issuperadmin)";

		Map<String, Object> bind = new HashMap<String, Object>(6);
		bind.put("username", userName);
		bind.put("password", encryptPassword(RealConstants.DEFAULT_PASSWORD));
		bind.put("status", true);
		bind.put("companyid", companyId);
		bind.put("employeeid", employeeId);
		bind.put("issuperadmin", false);

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "userid" };

		int usersInserted = namedParameterJdbcTemplate.update(userInsertSQL,
				paramSource, keyHolder, keyColumnName);

		if (usersInserted > 0) {
			String userId = keyHolder.getKey().toString();
			String userRolesInsertSQL = "Insert into userrole(userid,roleid,status) values (:userid,:roleid,:status)";

			bind = new HashMap<String, Object>(3);
			bind.put("userid", userId);
			bind.put("roleid", roleId);
			bind.put("status", true);

			paramSource = new MapSqlParameterSource(bind);

			int userRolesInserted = namedParameterJdbcTemplate.update(
					userRolesInsertSQL, paramSource);
			System.out.println("User roles Inserted : " + userRolesInserted);
		}

	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public GroupsResultDTO getGroup(GroupRegistrationDTO groupRegistrationDTO) {
		String groupSelectSQL = "SELECT companyid,shortkey,companyname,status,expirydate,address1,address2,address3,city,country,state,notes FROM company c where c.status=true and c.companyid like :companyid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("companyid", groupRegistrationDTO.getGroupid());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		GroupsResultDTO groupsResultDTO = namedParameterJdbcTemplate.query(
				groupSelectSQL, paramSource, groupResultSetExtractor);

		return groupsResultDTO;
	}

	public void updateGroup(GroupRegistrationDTO groupRegistrationDTO) {
		String groupUpdateSQL = "Update company SET companyname=:companyname, address1=:address1,address2=:address2,"
				+ "address3=:address3,city=:city,country=:country,state=:state,notes=:notes where companyid=:companyid";

		Map<String, Object> bind = new HashMap<String, Object>(9);
		bind.put("companyname", groupRegistrationDTO.getGroupName());
		bind.put("address1", groupRegistrationDTO.getAddress1());
		bind.put("address2", groupRegistrationDTO.getAddress2());
		bind.put("address3", groupRegistrationDTO.getAddress3());
		bind.put("city", groupRegistrationDTO.getCity());
		bind.put("country", groupRegistrationDTO.getCountry());
		bind.put("state", groupRegistrationDTO.getState());
		bind.put("notes", groupRegistrationDTO.getAdditionalInfo());
		bind.put("companyid", groupRegistrationDTO.getGroupid());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "companyid" };

		namedParameterJdbcTemplate.update(groupUpdateSQL, paramSource,
				keyHolder, keyColumnName);

	}

	public void deleteGroup(GroupRegistrationDTO groupRegistrationDTO) {
		String groupUpdateStatusSQL = "Update company SET status=false where companyid=:companyid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("companyid", groupRegistrationDTO.getGroupid());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(groupUpdateStatusSQL, paramSource);
	}

	public boolean isUniqueShortKey(String shortkey) {
		String isUniqueShortKeySQL = "Select count(*) from company where shortkey=:shortkey";

		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("shortkey", shortkey);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		Integer count = namedParameterJdbcTemplate.queryForObject(
				isUniqueShortKeySQL, paramSource, Integer.class);
		boolean result = true;
		System.out.println("count of shortkeys  : " + count);

		if (count > 0) {
			result = false;
		}

		return result;
	}
}
