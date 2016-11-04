<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Company</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form method="POST" commandName="groupRegistrationDTO"
				action="<%=RealConstants.GROUP_SAVE_URL%>" class="form-horizontal"
				role="form">
				<c:set var="formErrors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty formErrors}">
					<div class="alert alert-danger">Please solve the below errors
						and try submitting again.</div>
					<form:errors path="*" />
				</c:if>
				<div class="col-xs-20 col-sm-9">
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Company Name :</label>
							<div class="col-sm-8">
								<form:input path="groupName" cssClass="form-control"
									cssErrorClass="" maxlength="40" readonly="true" />
								<form:hidden path="groupid" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="empName" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="age" cssClass="form-control" cssErrorClass=""
									maxlength="40" />
								<form:hidden path="sex" cssClass="form-control" cssErrorClass=""
									maxlength="40" />
								<form:hidden path="empPhone" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<form:hidden path="username" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="groupNameError">
									<form:errors path="groupName" />
								</c:set>
								<c:if test="${not empty groupNameError}">
									<form:errors path="groupName" cssClass="help-inline" />
									<script type="text/javascript">
										$("#groupName").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Short Key :</label>
							<div class="col-sm-8">
								<form:input path="shortKey" cssClass="form-control"
									cssErrorClass="" maxlength="40" readonly="true" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Address Line 1 :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="address1" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
							</div>
							<c:set var="address1Error">
								<form:errors path="address1" />
							</c:set>
							<c:if test="${not empty address1Error}">
								<form:errors path="address1" cssClass="help-inline" />
								<script type="text/javascript">
									$("#address1").parent().parent().addClass(
											"has-error");
								</script>
							</c:if>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Address Line 2 :</label>
							<div class="col-sm-8">
								<form:input path="address2" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Address Line 3 :</label>
							<div class="col-sm-8">
								<form:input path="address3" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Country :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="country" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="countryError">
									<form:errors path="country" />
								</c:set>
								<c:if test="${not empty countryError}">
									<form:errors path="country" cssClass="help-inline" />
									<script type="text/javascript">
										$("#country").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">State :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="state" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="stateError">
									<form:errors path="state" />
								</c:set>
								<c:if test="${not empty stateError}">
									<form:errors path="state" cssClass="help-inline" />
									<script type="text/javascript">
										$("#state").parent().parent().addClass(
												"has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">City :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="city" cssClass="form-control" cssErrorClass=""
									maxlength="40" />
								<c:set var="cityError">
									<form:errors path="city" />
								</c:set>
								<c:if test="${not empty cityError}">
									<form:errors path="city" cssClass="help-inline" />
									<script type="text/javascript">
										$("#city").parent().parent().addClass(
												"has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Additional Info :</label>
							<div class="col-sm-8">
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
						href="<%=RealConstants.GROUP_ADD_URL%>" class="list-group-item">Add
						New Company</a> <a href="<%=RealConstants.GROUPS_ALL_VIEW%>"
						class="list-group-item">View Companies</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>