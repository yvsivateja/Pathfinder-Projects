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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.dto.ModuleDTO;
import com.pathfinder.dto.RolesDTO;
import com.pathfinder.dto.RolesResultDTO;
import com.pathfinder.resultsetextractors.RoleResultSetExtractor;

@Component
@Transactional
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private RoleResultSetExtractor roleResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void saveRoles(RolesDTO rolesDTO) {

		String roleInsertSQL = "INSERT INTO roles (rolename, status,isdefault,companyid) VALUES (:rolename, :status,:isdefault,:companyid)";

		Map<String, Object> bind = new HashMap<String, Object>(4);
		bind.put("rolename", rolesDTO.getRoleName());
		bind.put("status", true);
		bind.put("isdefault", false);
		bind.put("companyid", rolesDTO.getCompanyId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "roleid" };

		int rolesInserted = namedParameterJdbcTemplate.update(roleInsertSQL,
				paramSource, keyHolder, keyColumnName);
		if (rolesInserted > 0) {
			String roleId = keyHolder.getKey().toString();
			String roleACLInsertSQL = "INSERT INTO rolesacl (roleid, cancreate,canupdate,candelete,canview,module) VALUES (:roleid, :cancreate,:canupdate,:candelete,:canview,:module)";
			List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();
			for (ModuleDTO module : rolesDTO.getModules()) {
				bind = new HashMap<String, Object>(5);
				bind.put("roleid", roleId);
				bind.put("cancreate", module.isCanCreate());
				bind.put("canupdate", module.isCanUpdate());
				bind.put("candelete", module.isCanDelete());
				bind.put("canview", module.isCanView());
				bind.put("module", module.getModuleName());
				paramSourceList.add(new MapSqlParameterSource(bind));
			}
			int[] roleACLInserted = namedParameterJdbcTemplate.batchUpdate(
					roleACLInsertSQL,
					paramSourceList.toArray(new SqlParameterSource[0]));
			System.out.println("Modules Inserted : " + roleACLInserted.length);
		}

	}

	public void updateRole(RolesDTO rolesDTO) {

		String roleUpdateSQL = "UPDATE roles SET rolename=:rolename where roleid=:roleid";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("roleid", rolesDTO.getRoleId());
		bind.put("rolename", rolesDTO.getRoleName());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "roleid" };

		int rolesupdated = namedParameterJdbcTemplate.update(roleUpdateSQL,
				paramSource, keyHolder, keyColumnName);
		if (rolesupdated > 0) {
			String roleId = rolesDTO.getRoleId();
			String roleACLUpdateSQL = "UPDATE rolesacl "
					+ "SET  roleid=:roleid,cancreate=:cancreate, "
					+ "canupdate=:canupdate, candelete=:candelete, canview=:canview "
					+ "where rolesaclid=:rolesaclid";

			List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();
			for (ModuleDTO module : rolesDTO.getModules()) {
				bind = new HashMap<String, Object>(5);
				bind.put("cancreate", module.isCanCreate());
				bind.put("canupdate", module.isCanUpdate());
				bind.put("candelete", module.isCanDelete());
				bind.put("canview", module.isCanView());
				bind.put("rolesaclid", module.getModuleId());
				bind.put("roleid", roleId);
				paramSourceList.add(new MapSqlParameterSource(bind));
			}
			int[] roleACLUpdated = namedParameterJdbcTemplate.batchUpdate(
					roleACLUpdateSQL,
					paramSourceList.toArray(new SqlParameterSource[0]));
			System.out.println("Modules Updated : " + roleACLUpdated.length);
		}

	}

	public RolesResultDTO getRole(RolesDTO rolesDTO) {
		String roleSelectSQL = "SELECT "
				+ "r.roleid, r.rolename, r.status, r.isdefault, r.companyid, r.isdisplayed,"
				+ "rcl.rolesaclid, rcl.cancreate, rcl.canupdate, rcl.candelete, rcl.canview,rcl.module "
				+ "FROM roles r JOIN rolesacl rcl ON rcl.roleid=r.roleid "
				+ "where r.roleid like :roleid and r.companyid=:companyid and "
				+ "isdisplayed = true and status=true ORDER BY r.roleid,rcl.module ";
		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("companyid", rolesDTO.getCompanyId());
		bind.put("roleid", rolesDTO.getRoleId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		RolesResultDTO rolesResultDTO = namedParameterJdbcTemplate.query(
				roleSelectSQL, paramSource, roleResultSetExtractor);
		System.out.println("No of roles retrieved  : "
				+ rolesResultDTO.getRolesList().size());
		return rolesResultDTO;
	}

	public void deleteRole(RolesDTO rolesDTO) {
		String roleUpdateStatusSQL = "Update roles SET status=false where roleid=:roleid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("roleid", rolesDTO.getRoleId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(roleUpdateStatusSQL, paramSource);

	}
}
