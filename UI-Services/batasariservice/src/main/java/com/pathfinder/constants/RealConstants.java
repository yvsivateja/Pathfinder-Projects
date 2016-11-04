package com.pathfinder.constants;

public class RealConstants {

	public static final String USER_SAVE_URL = "usersave.htm";
	public static final String USER_VIEW_PAGE = "showUsers";
	public static final String USER_ADD_URL = "useradd.htm";
	public static final String USER_UPDATE_PAGE = "updateUser";
	public static final String USERS_ALL_VIEW = "userview.htm";
	public static final String USER_ADD_PAGE = "adduser";
	public static final String USER_MANAGE_ACTIONS = "useraction.htm";
	public static final String USER_PROFILE_VIEW = "userProfile.htm";
	public static final String USER_PROFILE_PAGE = "showuserprofile";
	public static final String USER_CHANGE_PASSWORD = "changePassword.htm";
	public static final String USER_PASSWORD_CHANGE_PAGE = "changepassword";

	public static final String GROUP_SAVE_URL = "groupsave.htm";
	public static final String GROUP_ADD_URL = "groupadd.htm";
	public static final String GROUP_MANAGE_ACTIONS = "groupaction.htm";
	public static final String GROUP_UPDATE_PAGE = "groupupdate";
	public static final String GROUPS_ALL_VIEW = "groupview.htm";
	public static final String GROUP_ADD_PAGE = "groupregistration";
	public static final String GROUPS_SHOW_PAGE = "showGroups";
	public static final String GROUPS_UPDATE_PAGE = "updateGroup";

	public static final String EMPLOYEE_SAVE_URL = "employeesave.htm";
	public static final String EMPLOYEE_ADD_URL = "employeeadd.htm";
	public static final String EMPLOYEE_MANAGE_ACTIONS = "employeeaction.htm";
	public static final String EMPLOYEE_UPDATE_PAGE = "updateEmployee";
	public static final String EMPLOYEE_ALL_VIEW = "employeeview.htm";
	public static final String EMPLOYEE_ADD_PAGE = "employeeregistration";
	public static final String EMPLOYEES_SHOW_PAGE = "showEmployees";

	public static final String ROLE_SAVE_URL = "rolesave.htm";
	public static final String ROLE_ADD_URL = "roleadd.htm";
	public static final String ROLE_MANAGE_ACTIONS = "roleaction.htm";
	public static final String ROLE_ALL_VIEW = "roleview.htm";
	public static final String ROLE_ALL_PAGE = "showRoles";
	public static final String ROLE_ADD_PAGE = "roleregistration";
	public static final String ROLE_GET_ACL = "roleACL.htm";
	public static final String ROLE_UPDATE_PAGE = "updateRole";

	public static final String INACTIVE_DEVICES = "inactivedevices.htm";
	public static final String INACTIVE_DEVICES_PAGE = "inactivedevices";
	public static final String DEVICES_TO_APPROVE = "devicesToApprove.htm";
	public static final String DEVICES_TO_APPROVE_PAGE = "devicestoapprove";
	public static final String APPROVE_DEVICES = "approvedevices.htm";
	public static final String DEVICES_VIEW_ALL = "showdevices.htm";
	public static final String DEVICES_VIEW_PAGE = "showdevices";
	public static final String DEVICE_MANAGE_ACTION = "deviceaction.htm";
	public static final String DEVICE_LOCATION_TABULAR = "showtabulardevicelocations";
	public static final String DEVICE_SHOW_LOCATIONS_TABULAR = "showtabulardevicelocations.htm";
	public static final String DEVICE_SHOW_LOCATIONS = "showdevicelocations.htm";
	public static final String DEVICE_SHOW_MAP_POINTS = "showmappoints";

	public static final String SCHEDULER_URL = "scheduler.htm";
	public static final String SCHEDULER_PAGE = "scheduler";
	public static final String USER_HOME = "userHome.htm";
	public static final String DEFAULT_PASSWORD = "changePassword@123";
	public static final String ACCESS_DENIED_PAGE = "accessdenied";
	public static final String REMOVE_IMPERSONATION = "removeImpersonation.htm";

	public static final String AUTH_REG_DEVICE = "authAndRegDevice.htm";
	public static final String SAVE_DEVICE_LOACATION = "saveLocation.htm";

	public static enum MODULENAME {
		COMPANY, EMPLOYEE, USERS, ROLES, IMPERSONATE, APPROVEDEVICES, DEVICES, DEVICELOCATION
	};

	public static final String[] MODULES = { MODULENAME.COMPANY.name(),
			MODULENAME.EMPLOYEE.name(), MODULENAME.USERS.name(),
			MODULENAME.ROLES.name(), MODULENAME.IMPERSONATE.name(),
			MODULENAME.APPROVEDEVICES.name(), MODULENAME.DEVICES.name(),
			MODULENAME.DEVICELOCATION.name() };
	

}
