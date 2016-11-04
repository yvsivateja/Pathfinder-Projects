<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>

</head>
<body>
	<div class="container">


		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>

		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">
			<%
				String message = "";
				message = request.getParameter("message");
				if (message != null)
					if (!message.isEmpty()) {
			%>
			<div class="alert alert-success" role="alert">
				<strong>Password changed successfully!</strong>
			</div>
			<%
				}
			%>
			<form:form commandName="userProfileDTO" action="#"
				class="form-horizontal" role="form">
				<div class="col-xs-20 col-sm-9">
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Name :</label>
							<div class="col-sm-8">
								<p class="form-control-static">${userProfileDTO.empName}</p>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Phone :</label>
							<div class="col-sm-8">
								<p class="form-control-static">${userProfileDTO.empPhone}</p>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Email ID :</label>
							<div class="col-sm-8">
								<p class="form-control-static">${userProfileDTO.empEmailid}</p>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Age :</label>
							<div class="col-sm-8">
								<p class="form-control-static">${userProfileDTO.age}</p>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">User Name :</label>
							<div class="col-sm-8">
								<p class="form-control-static">${userProfileDTO.username}</p>
							</div>
						</div>
					</div>

				</div>
			</form:form>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.USER_PROFILE_VIEW%>?changePassword=true"
						class="list-group-item">Change Password</a> <a href="logout.htm"
						class="list-group-item">Logout</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>