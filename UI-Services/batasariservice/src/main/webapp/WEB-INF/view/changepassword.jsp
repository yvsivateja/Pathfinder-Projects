<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>

</head>
<body>
	<div class="container">


		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>

		<div class="row">&nbsp;</div>
		<div class="row row-offcanvas row-offcanvas-right">

			<form:form commandName="userProfileDTO" method="post"
				action="<%=RealConstants.USER_CHANGE_PASSWORD%>"
				class="form-horizontal" role="form">
				<div class="col-xs-20 col-sm-9">
					<div class="col-sm-13 main">
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">User Name :</label>
							<div class="col-sm-8">
								<form:hidden path="userId" cssClass="form-control"
									maxlength="40" />
								<form:hidden path="username" cssClass="form-control"
									maxlength="40" />
								<p class="form-control-static">${userProfileDTO.username}</p>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Password :</label>
							<div class="col-sm-8">
								<form:password path="password" cssClass="form-control"
									cssErrorClass="" maxlength="40" />

								<c:set var="passwordError">
									<form:errors path="password" />
								</c:set>
								<c:if test="${not empty passwordError}">
									<form:errors path="password" cssClass="help-inline" />
									<script type="text/javascript">
										$("#password").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label class="col-sm-3 control-label">Confirm password :</label>
							<div class="col-sm-8">
								<form:password path="repassword" cssClass="form-control"
									cssErrorClass="" maxlength="40" />

								<c:set var="repasswordError">
									<form:errors path="repassword" />

								</c:set>

								<c:if test="${not empty repasswordError}">
									<form:errors path="repassword" cssClass="help-inline" />
									<script type="text/javascript">
										$("#repassword").parent().parent()
												.addClass("has-error");
									</script>
								</c:if>

								<c:set var="repasswordError">
									<form:errors path="rePasswordValid" />
								</c:set>

								<c:if test="${not empty repasswordError}">
									<form:errors path="rePasswordValid" cssClass="help-inline" />
									<script type="text/javascript">
										$("#repassword").parent().parent()
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
								style="margin-right: 10%;" name="actionType" value="add">Save</button>
							<button class="btn btn-primary" type="reset">Reset</button>
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