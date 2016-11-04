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

import com.pathfinder.dto.UserProfileDTO;
import com.pathfinder.dto.UserRegistrationDTO;
import com.pathfinder.dto.UsersResultDTO;
import com.pathfinder.resultsetextractors.AvailableEmployeeResultSetExtractor;
import com.pathfinder.resultsetextractors.AvailableRoleResultSetExtractor;
import com.pathfinder.resultsetextractors.RetrieveUserRoleResultSetExtractor;
import com.pathfinder.resultsetextractors.UserProfileResultSetExtractor;
import com.pathfinder.resultsetextractors.UsersResultSetExtractor;

@Component
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private AvailableEmployeeResultSetExtractor availableEmployeeResultSetExtractor;

	@Autowired
	private AvailableRoleResultSetExtractor availableRoleResultSetExtractor;

	@Autowired
	private RetrieveUserRoleResultSetExtractor retrieveUserRoleResultSetExtractor;

	@Autowired
	private UsersResultSetExtractor usersResultSetExtractor;

	@Autowired
	private UserProfileResultSetExtractor userProfileResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void saveUser(UserRegistrationDTO userRegistrationDTO) {

		String userInsertSQL = "INSERT INTO users (username, password,status,companyid,employeeid,issuperadmin) VALUES (:username, :password,:status,:companyid,:employeeid,:issuperadmin)";

		Map<String, Object> bind = new HashMap<String, Object>(6);
		bind.put("username", userRegistrationDTO.getUsername());
		bind.put("password", encryptPassword(userRegistrationDTO.getPassword()));
		bind.put("status", true);
		bind.put("companyid", userRegistrationDTO.getGroupId());
		bind.put("employeeid", userRegistrationDTO.getEmployeeId());
		bind.put("issuperadmin", userRegistrationDTO.isSuperAdmin());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "userid" };

		int usersInserted = namedParameterJdbcTemplate.update(userInsertSQL,
				paramSource, keyHolder, keyColumnName);

		if (usersInserted > 0) {
			String userId = keyHolder.getKey().toString();
			String userRolesInsertSQL = "Insert into userrole(userid,roleid,status) values (:userid,:roleid,:status)";
			List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();
			for (String roleId : userRegistrationDTO.getSelectedroles()) {
				bind = new HashMap<String, Object>(3);
				bind.put("userid", userId);
				bind.put("roleid", roleId);
				bind.put("status", true);
				paramSourceList.add(new MapSqlParameterSource(bind));
			}
			int[] userRolesInserted = namedParameterJdbcTemplate.batchUpdate(
					userRolesInsertSQL,
					paramSourceList.toArray(new SqlParameterSource[0]));
			System.out.println("User roles Inserted : "
					+ userRolesInserted.length);
		}

	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public UsersResultDTO getUser(UserRegistrationDTO userRegistrationDTO) {
		String getUserSQL = "Select u.username,u.userid,e.employeename,GROUP_CONCAT(r.rolename SEPARATOR ', ') as rolenames "
				+ "from users u "
				+ "JOIN employee e ON u.employeeid=e.employeeid "
				+ "JOIN userrole ur ON ur.userid=u.userid "
				+ "JOIN roles r ON r.roleid=ur.roleid "
				+ "where u.userid like :userid and u.companyid=:companyid and u.status=true "
				+ "group by u.userid";

		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("companyid", userRegistrationDTO.getGroupId());
		bind.put("userid", userRegistrationDTO.getUserId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		UsersResultDTO usersResultDTO = namedParameterJdbcTemplate.query(
				getUserSQL, paramSource, usersResultSetExtractor);
		System.out.println("No of roles retrieved  : "
				+ usersResultDTO.getUserResults().size());

		return usersResultDTO;
	}

	public boolean isUniqueUserName(String userName) {
		String getUserSQL = "Select count(*) from users where username=:username";

		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("username", userName);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		Integer count = namedParameterJdbcTemplate.queryForObject(getUserSQL,
				paramSource, Integer.class);
		boolean result = true;
		System.out.println("count of usernames  : " + count);

		if (count > 0) {
			result = false;
		}

		return result;
	}

	public void updateUser(UserRegistrationDTO userRegistrationDTO) {
		deleteUserRoles(userRegistrationDTO);

		String userRolesInsertSQL = "Insert into userrole(userid,roleid,status) values (:userid,:roleid,:status)";
		List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();

		Map<String, Object> bind = new HashMap<String, Object>(2);

		for (String roleId : userRegistrationDTO.getSelectedroles()) {
			bind = new HashMap<String, Object>(3);
			bind.put("userid", userRegistrationDTO.getUserId());
			bind.put("roleid", roleId);
			bind.put("status", true);
			paramSourceList.add(new MapSqlParameterSource(bind));
		}
		int[] userRolesInserted = namedParameterJdbcTemplate.batchUpdate(
				userRolesInsertSQL,
				paramSourceList.toArray(new SqlParameterSource[0]));
		System.out.println("User roles Inserted : " + userRolesInserted.length);

	}

	public void deleteUser(UserRegistrationDTO userRegistrationDTO) {
		deleteUserRoles(userRegistrationDTO);

		String deleteUsersSQL = "delete from users where userid=:userid";

		Map<String, Object> bind = new HashMap<String, Object>(1);

		bind.put("userid", userRegistrationDTO.getUserId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		int deletedRecords = namedParameterJdbcTemplate.update(deleteUsersSQL,
				paramSource);

		System.out
				.println("No of userrole records deleted : " + deletedRecords);

	}

	public int deleteUserRoles(UserRegistrationDTO userRegistrationDTO) {
		String deleteUserRolesSQL = "delete from userrole where userid=:userid";

		Map<String, Object> bind = new HashMap<String, Object>(1);

		bind.put("userid", userRegistrationDTO.getUserId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		int deletedRecords = namedParameterJdbcTemplate.update(
				deleteUserRolesSQL, paramSource);

		System.out
				.println("No of userrole records deleted : " + deletedRecords);
		return deletedRecords;
	}

	public Map<String, String> getAvailableEmployees(String companyid) {
		String availableEmployeesSQL = "select * from employee where employeeid NOT IN (Select employeeid from users) and companyid=:companyid and status=true";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("companyid", companyid);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		Map<String, String> availableEmployeeList = namedParameterJdbcTemplate
				.query(availableEmployeesSQL, paramSource,
						availableEmployeeResultSetExtractor);
		return availableEmployeeList;
	}

	public Map<String, String> getAvailableRoles(String companyid) {
		String availableRolesSQL = "Select * from roles r where r.companyid=:companyid and isdisplayed=true and status=true";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("companyid", companyid);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		Map<String, String> availableRolesList = namedParameterJdbcTemplate
				.query(availableRolesSQL, paramSource,
						availableRoleResultSetExtractor);
		return availableRolesList;
	}

	public UserRegistrationDTO getUserRoles(
			UserRegistrationDTO userRegistrationDTO) {
		String userRolesSQL = "Select r.roleid,r.rolename,ur.userid,u.username,e.employeename "
				+ "From roles r "
				+ "LEFT JOIN userrole ur ON ur.roleid=r.roleid and ur.userid=:userid "
				+ "LEFT JOIN users u ON u.userid=ur.userid "
				+ "LEFT JOIN employee e ON e.employeeid=u.employeeid "
				+ "where r.status=true and r.isdisplayed=true and r.companyid=:companyid "
				+ "order by r.roleid";

		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("companyid", userRegistrationDTO.getGroupId());
		bind.put("userid", userRegistrationDTO.getUserId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		userRegistrationDTO = namedParameterJdbcTemplate.query(userRolesSQL,
				paramSource, retrieveUserRoleResultSetExtractor);
		return userRegistrationDTO;
	}

	public UserProfileDTO getUserProfile(String loggedInUserId) {
		String userProfileSQL = "SELECT u.userid,u.username,u.password,u.companyid,u.employeeid,u.issuperadmin,"
				+ "e.employeename,e.age,e.sex,e.phone,e.emailid,e.notes "
				+ "FROM users u JOIN employee e ON e.employeeid=u.employeeid where u.userid=:userid";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("userid", loggedInUserId);

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		UserProfileDTO userProfileDTO = namedParameterJdbcTemplate.query(
				userProfileSQL, paramSource, userProfileResultSetExtractor);
		return userProfileDTO;
	}

	public void changeUserPassword(UserProfileDTO userProfileDTO) {
		String userPasswordUpdateSQL = "Update users SET password=:password where userid=:userid";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("userid", userProfileDTO.getUserId());
		bind.put("password", encryptPassword(userProfileDTO.getPassword()));

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(userPasswordUpdateSQL, paramSource);
		System.out.println("Password changed");

	}

}
