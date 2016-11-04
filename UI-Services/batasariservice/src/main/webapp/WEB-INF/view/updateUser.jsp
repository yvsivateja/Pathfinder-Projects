<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<script src="js/multiselect.js"></script>
<link href="css/multiselect.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form method="POST" commandName="userRegistrationDTO"
				action="<%=RealConstants.USER_SAVE_URL%>" class="form-horizontal"
				role="form" onsubmit="return selectall();">
				<c:set var="formErrors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty formErrors}">
					<div class="alert alert-danger">Please solve the below errors
						and try submitting again.</div>
				</c:if>
				<div class="col-xs-20 col-sm-9">
					<h3>User Registration</h3>
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">User Name :</label>
							<div class="col-sm-9">
								<form:input path="username" cssClass="form-control"
									cssErrorClass="formerror" maxlength="40" readonly="true" />
								<form:hidden path="userId" cssClass="form-control"
									maxlength="40" />
								<form:hidden path="employeeId" cssClass="form-control" />
								<form:hidden path="password" cssClass="form-control" />
								<form:hidden path="repassword" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Employee :</label>
							<div class="col-sm-9">
								<form:input path="employeeName" cssClass="form-control"
									cssErrorClass="formerror" maxlength="40" readonly="true" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Roles :</label>
							<div class="col-sm-4">
								<form:select class="form-control" path="availableroles"
									multiple="multiple" id="available" style="height:auto">
									<form:options
										items="${userRegistrationDTO.availableRolesTODisplay}" />
								</form:select>
							</div>
							<div class="col-sm-1">
								<div class="select-options">
									<a href="#" id="add">A</a>
								</div>
								<div class="select-options">
									<a href="#" id="remove">R</a>
								</div>
							</div>
							<div class="col-sm-4">
								<form:select class="form-control" path="selectedroles"
									multiple="multiple" id="choosen" style="height:auto">
									<form:options
										items="${userRegistrationDTO.selectedRolesTODisplay}" />
								</form:select>
								<c:set var="selectedrolesError">
									<form:errors path="selectedroles" />
								</c:set>

								<c:if test="${not empty selectedrolesError}">
									<form:errors path="selectedroles" cssClass="help-inline" />
									<script type="text/javascript">
										$("#selectedroles").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
					</div>
					<div class="col-sm-13 main">
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="update">Update</button>
							<button class="btn btn-primary" type="reset">Reset</button>
						</div>
					</div>
				</div>
			</form:form>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.USER_ADD_URL%>" class="list-group-item">Add
						New User</a><a href="<%=RealConstants.USERS_ALL_VIEW%>"
						class="list-group-item">View Users</a><a href="#"
						class="list-group-item">Add New Employee</a> <a
						href="<%=RealConstants.EMPLOYEE_ALL_VIEW%>"
						class="list-group-item">View Employees</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>