<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Role</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<script src="js/rolescript.js"></script>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form method="POST" commandName="rolesDTO"
				action="<%=RealConstants.ROLE_SAVE_URL%>" class="form-horizontal"
				role="form">
				<c:set var="formErrors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty formErrors}">
					<div class="alert alert-danger">Please solve the below errors
						and try submitting again.</div>
				</c:if>
				<div class="col-xs-20 col-sm-9">
					<h3>Role Registration</h3>
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Name :</label>
							<div class="col-sm-9">
								<form:input path="roleName" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="companyId" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="roleId" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="roleNameError">
									<form:errors path="roleName" />
								</c:set>
								<c:if test="${not empty roleNameError}">
									<form:errors path="roleName" cssClass="help-inline" />
									<script type="text/javascript">
										$("#roleName").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<c:forEach items="${rolesDTO.modules}" var="module"
							varStatus="status">
							<div class="form-group form-group-sm">
								<label class="col-sm-3 control-label">${module.moduleName}
									:</label>
								<div class="col-sm-9">
									<form:hidden path="modules[${status.index}].moduleName" />
									<form:hidden path="modules[${status.index}].moduleId" />
									<label class="checkbox-inline"> <form:checkbox
											path="modules[${status.index}].canCreate"
											id="inlineCheckbox1"
											cssClass="nonview_check_box-${status.index}" /> Create
									</label> <label class="checkbox-inline"> <form:checkbox
											path="modules[${status.index}].canUpdate"
											id="inlineCheckbox2"
											cssClass="nonview_check_box-${status.index}" /> Update
									</label> <label class="checkbox-inline"> <form:checkbox
											path="modules[${status.index}].canDelete"
											id="inlineCheckbox3"
											cssClass="nonview_check_box-${status.index}" /> Delete
									</label> <label class="checkbox-inline"> <form:checkbox
											path="modules[${status.index}].canView" id="inlineCheckbox4"
											cssClass="view_checkbox-${status.index}" /> View
									</label>
								</div>
							</div>
						</c:forEach>
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
						href="<%=RealConstants.ROLE_ADD_URL%>" class="list-group-item">Add
						New Role</a> <a href="<%=RealConstants.ROLE_ALL_VIEW%>"
						class="list-group-item">View Roles</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>