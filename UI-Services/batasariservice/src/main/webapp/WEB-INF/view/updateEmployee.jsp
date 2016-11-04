<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form method="POST" commandName="employeeRegistrationDTO"
				action="<%=RealConstants.EMPLOYEE_SAVE_URL%>"
				class="form-horizontal" role="form">
				<c:set var="formErrors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty formErrors}">
					<div class="alert alert-danger">Please solve the below errors
						and try submitting again.</div>
				</c:if>
				<div class="col-xs-20 col-sm-9">
					<h3>Update ${employeeRegistrationDTO.empName}</h3>
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Name :</label>
							<div class="col-sm-9">
								<form:input path="empName" cssClass="form-control"
									cssErrorClass="" maxlength="40" readonly="true" />
								<form:hidden path="groupid" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="employeeId" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Email Id :</label>
							<div class="col-sm-9">
								<form:input path="empEmailid" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="empEmailidError">
									<form:errors path="empEmailid" />
								</c:set>
								<c:if test="${not empty empEmailidError}">
									<form:errors path="empEmailid" cssClass="help-inline" />
									<script type="text/javascript">
										$("#empEmailid").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Contact No :</label>
							<div class="col-sm-9">
								<form:input path="empPhone" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="empPhoneError">
									<form:errors path="empPhone" />
								</c:set>
								<c:if test="${not empty empPhoneError}">
									<form:errors path="empPhone" cssClass="help-inline" />
									<script type="text/javascript">
										$("#empPhone").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Age :</label>
							<div class="col-sm-9">
								<form:input path="age" cssClass="form-control" cssErrorClass=""
									maxlength="40" />
								<c:set var="ageError">
									<form:errors path="age" />
								</c:set>
								<c:if test="${not empty ageError}">
									<form:errors path="age" cssClass="help-inline" />
									<script type="text/javascript">
										$("#age").parent().parent().addClass(
												"has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Gender :</label>
							<div class="col-sm-9">
								<label class="checkbox-inline"> <form:radiobutton
										path="sex" value="M" /> Male
								</label> <label class="checkbox-inline"> <form:radiobutton
										path="sex" value="F" /> Female
								</label>
								<c:set var="sexError">
									<form:errors path="sex" />
								</c:set>
								<c:if test="${not empty sexError}">
									<form:errors path="sex" cssClass="help-inline" element="div" />
									<script type="text/javascript">
										$("input[id^=sex]").parent().parent()
												.parent().addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label">Additional Info :</label>
							<div class="col-sm-9">
								<form:textarea path="additionalInfo" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
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
						href="<%=RealConstants.EMPLOYEE_ADD_URL%>" class="list-group-item">Add
						New Employee</a> <a href="<%=RealConstants.EMPLOYEE_ALL_VIEW%>"
						class="list-group-item">View Employees</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>