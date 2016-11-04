<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Registration</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<script src="js/script.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#usercompany").html($("#shortKey").val());
		$('#groupName').keyup(function() {
			prepareShortKey($(this).val());
		});
		$("#groupName").change(function() {
			prepareShortKey($(this).val());
		});

		$("#shortKey").change(function() {
			$("#usercompany").html($(this).val());
		});
	});

	function prepareShortKey(value) {

		var splitString = value.trim().split(" ");
		var constructedKey = "";
		for (var i = 0; i < splitString.length; i++) {
			if (splitString.length < 5) {
				if (splitString.length == 1) {
					constructedKey += splitString[i].charAt(0)
							+ splitString[i].charAt(1)
							+ splitString[i].charAt(2);
				} else {
					constructedKey += splitString[i].charAt(0);
				}
			}
		}

		$("#shortKey").val(constructedKey.toLowerCase());
		$("#usercompany").html(constructedKey.toLowerCase());
	}
</script>
</head>
<body>
	<div class="container">


		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>

		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form method="POST" commandName="groupRegistrationDTO"
				action="<%=RealConstants.GROUP_SAVE_URL%>" class="form-horizontal"
				role="form" onsubmit="return onSubmitGroupRegistration();">
				<c:set var="formErrors">
					<form:errors path="*" />
				</c:set>
				<c:if test="${not empty formErrors}">
					<div class="alert alert-danger">Please solve the below errors
						and try submitting again.</div>
				</c:if>

				<div class="col-xs-20 col-sm-9">
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Company Name :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="groupName" cssClass="form-control"
									maxlength="40" />
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
							<label class="col-sm-3 control-label">Short Key : <span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="shortKey" cssClass="form-control"
									cssErrorClass="" maxlength="4"
									title="username will be appended with shortkey eg., shortkey=ABC, username will be somex@ABC" />
								<c:set var="shortKeyError">
									<form:errors path="shortKey" />
								</c:set>
								<c:if test="${not empty shortKeyError}">
									<form:errors path="shortKey" cssClass="help-inline" />
									<script type="text/javascript">
										$("#shortKey").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>

							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Admin Name : <span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="empName" cssClass="form-control"
									cssErrorClass="" maxlength="40" />
								<c:set var="empNameError">
									<form:errors path="empName" />
								</c:set>
								<c:if test="${not empty empNameError}">
									<form:errors path="empName" cssClass="help-inline" />
									<script type="text/javascript">
										$("#empName").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>

							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">User Name :</label>
							<div class="col-sm-7">
								<form:input path="userNameDisplay" cssClass="form-control"
									maxlength="40" />
								<form:hidden path="username" cssClass="form-control"
									maxlength="40" />
								<c:set var="usernameError">
									<form:errors path="username" />
								</c:set>
								<c:if test="${not empty usernameError}">
									<form:errors path="username" cssClass="help-inline" />
									<script type="text/javascript">
										$("#username").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
							<div class="col-sm-1">
								<label class="control-label">@<span id="usercompany"></span></label>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Address Line 1 : <span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
								<form:input path="address1" cssClass="form-control"
									cssErrorClass="" maxlength="40" />

								<c:set var="address1Error">
									<form:errors path="address1" />
								</c:set>
								<c:if test="${not empty address1Error}">
									<form:errors path="address1" cssClass="help-inline" />
									<script type="text/javascript">
										$("#address1").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>


							</div>
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
								class="mandatory"> *</span>
							</label>
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
							<label class="col-sm-3 control-label">State : <span
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
							<label class="col-sm-3 control-label">Contact No :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
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
							<label class="col-sm-3 control-label">Age :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
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
							<label class="col-sm-3 control-label">Gender :<span
								class="mandatory"> *</span></label>
							<div class="col-sm-8">
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
							<label class="col-sm-3 control-label">Additional Info :</label>
							<div class="col-sm-8">
								<form:textarea path="additionalInfo" cssClass="form-control"
									cssErrorClass="" maxlength="40" rows="3"></form:textarea>
							</div>
						</div>
					</div>
					<div class="col-sm-13 main">
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="add">Save</button>
							<button class="btn btn-primary" type="reset">Reset</button>
						</div>
					</div>
				</div>
			</form:form>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a href="#"
						class="list-group-item">Add New Company</a> <a
						href="<%=RealConstants.GROUPS_ALL_VIEW%>" class="list-group-item">View
						Companies</a>
				</div>
			</div>

		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

	</div>
</body>
</html>