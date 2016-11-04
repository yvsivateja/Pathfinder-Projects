<%@page import="com.pathfinder.filter.ModuleBean"%>
<%@page import="java.util.Map"%>
<%@page import="com.pathfinder.filter.SessionBean"%>
<%@page import="com.pathfinder.constants.RealConstants"%>
<div class="masthead">
	<%
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean == null) {
			request.getSession().invalidate();
			response.sendRedirect("userLogin.htm");
		}
	%>
	<div class="row">
		<div class="col-xs-20 col-sm-10">
			<h3 class="text-muted">
				<%=sessionBean.getCompanyName().toUpperCase()%>
			</h3>
		</div>
		<div class="col-xs-9 col-sm-2">

			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown">
					My Account <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="<%=RealConstants.USER_PROFILE_VIEW%>">My Profile</a></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="<%=RealConstants.USER_PROFILE_VIEW%>?changePassword=true">Change
							Password</a></li>
					<%
						if (sessionBean.isSuperAdmin()) {
					%>
					<li role="presentation" class="divider"></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="<%=RealConstants.SCHEDULER_URL%>">Scheduler Log</a></li>
					<%
						}
					%>
					<li role="presentation" class="divider"></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="logout.htm">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>

	<ul class="nav nav-justified">
		<li><a href="<%=RealConstants.USER_HOME%>">Home</a></li>
		<%
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());
			if (employeeModuleBean.isCanView()
					&& !sessionBean.getHomePage().contentEquals(
							RealConstants.MODULENAME.EMPLOYEE.name())) {
		%>
		<li><a href="<%=RealConstants.EMPLOYEE_ALL_VIEW%>">Employees</a></li>
		<%
			}
		%>
		<%
			if (rolesModuleBean.isCanView()
					&& !sessionBean.getHomePage().contentEquals(
							RealConstants.MODULENAME.ROLES.name())) {
		%>
		<li><a href="<%=RealConstants.ROLE_ALL_VIEW%>">Roles</a></li>
		<%
			}
		%>
		<%
			if (usersModuleBean.isCanView()
					&& !sessionBean.getHomePage().contentEquals(
							RealConstants.MODULENAME.USERS.name())) {
		%>
		<li><a href="<%=RealConstants.USERS_ALL_VIEW%>">Users</a></li>
		<%
			}
		%>
		<li><a href="<%=RealConstants.DEVICES_VIEW_ALL%>">Devices</a></li>
		<li><a href="<%=RealConstants.DEVICES_TO_APPROVE%>">Devices to approve</a></li>
	</ul>
</div>
<%
	if (sessionBean.isImpersonated()) {
%>
<div class="row">
	<div class="col-xs-20 col-sm-11">
		<span class="tag label label-info"> <a
			href="<%=RealConstants.REMOVE_IMPERSONATION%>"><i
				class="remove glyphicon glyphicon-remove-sign glyphicon-white"></i></a>
			<span>Close Impersonation</span></span>
	</div>
</div>
<%
	}
%>