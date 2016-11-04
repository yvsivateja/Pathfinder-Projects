package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;

@Component
public class SessionBeanResultSetExtractor implements
		ResultSetExtractor<SessionBean> {

	public SessionBean extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		System.out.println("In Resultset");
		SessionBean sessionBean = new SessionBean();
		Map<Integer, String> roles = new HashMap<Integer, String>();
		Map<String, ModuleBean> modules = new HashMap<String, ModuleBean>();
		while (rs.next()) {
			sessionBean.setCompanyid(rs.getString("companyid"));
			sessionBean.setUserCompanyid(rs.getString("companyid"));
			sessionBean.setLoggedInUserId(rs.getString("userid"));
			sessionBean.setLoggedInUserName(rs.getString("username"));
			sessionBean.setSuperAdmin(rs.getBoolean("issuperadmin"));
			sessionBean.setCompanyName(rs.getString("companyname"));
			sessionBean.setCompanyShortKey(rs.getString("shortkey"));
			roles.put(rs.getInt("roleid"), rs.getString("rolename"));

			ModuleBean moduleBean = new ModuleBean();
			ModuleBean previousModuleBean = modules.get(rs.getString("module"));
			if (previousModuleBean != null) {
				if (previousModuleBean.isCanAdd() || rs.getBoolean("cancreate")) {
					moduleBean.setCanAdd(true);
				} else {
					moduleBean.setCanAdd(false);
				}
				if (previousModuleBean.isCanUpdate()
						|| rs.getBoolean("canupdate")) {
					moduleBean.setCanUpdate(true);
				} else {
					moduleBean.setCanUpdate(false);
				}
				if (previousModuleBean.isCanDelete()
						|| rs.getBoolean("candelete")) {
					moduleBean.setCanDelete(true);
				} else {
					moduleBean.setCanDelete(false);
				}
				if (previousModuleBean.isCanView() || rs.getBoolean("canview")) {
					moduleBean.setCanView(true);
				} else {
					moduleBean.setCanView(false);
				}
			} else {
				moduleBean.setCanAdd(rs.getBoolean("cancreate"));
				moduleBean.setCanUpdate(rs.getBoolean("canupdate"));
				moduleBean.setCanDelete(rs.getBoolean("candelete"));
				moduleBean.setCanView(rs.getBoolean("canview"));

			}

			modules.put(rs.getString("module"), moduleBean);
		}
		sessionBean.setRoles(roles);
		sessionBean.setModulePermissions(modules);
		return sessionBean;
	}
}
