<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registerd Roles</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<!-- <link href="css/dataTables.bootstrap.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script> -->
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#resultbody>tr:first").trigger('click');

		var row=$("#resultbody>tr:first");
		$(row).addClass("danger");
		$("#formaction").show("fade");
				
		$("#resultbody tr").click(function() {
			$("#resultbody tr").removeClass("danger");
			$(this).addClass("danger");
			$("#formaction").show("fade");
		});
		
		/* $("#bootstrap_table").dataTable({
			"scrollCollapse": true,
			"scrollY" : "270px"
		}); */

	});
	function setRole(value) {
		$("#hidden_key").val(value);
		$("[id^=role_]").hide();
		var tbodyId="#role_"+value;
		
		$(tbodyId).show("fade");
	}
	
</script>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-15 col-sm-8">
				<div class="col-sm-11 main">
					<h5>Click on the Role to perform actions</h5>
				</div>

			</div>
		</div>
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-20 col-sm-9">
				<div class="col-sm-11 main">

					<div class="table-responsive">
						<table class="table table-hover table-bordered"
							id="bootstrap_table">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Role Name</th>

								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${RolesResultDTO.rolesList ne null}">
									<c:forEach var="RolesDTO" items="${RolesResultDTO.rolesList}"
										varStatus="serial">
										<tr onclick="setRole(${RolesDTO.roleId})">
											<td>${serial.count}</td>
											<td>${RolesDTO.roleName}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-11 main">
					<form:form method="POST" commandName="rolesDTO"
						action="<%=RealConstants.ROLE_MANAGE_ACTIONS%>" role="form"
						id="formaction">
						<form:hidden path="roleId" id="hidden_key" />
						<div class="row">&nbsp;</div>
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="update">Update</button>
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="delete">Delete</button>
						</div>

					</form:form>
				</div>
				<div class="col-sm-11 main">
					<div class="table-responsive">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th>S.No</th>
									<th>Module Name</th>
									<th>Can Create</th>
									<th>Can Update</th>
									<th>Can Delete</th>
									<th>Can View</th>
								</tr>
							</thead>

							<c:if test="${RolesResultDTO.rolesList ne null}">
								<c:forEach var="RolesDTO" items="${RolesResultDTO.rolesList}">
									<tbody id="role_${RolesDTO.roleId}" style="display: none">
										<c:forEach var="module" items="${RolesDTO.modules}"
											varStatus="module_serial">
											<tr>
												<td>${module_serial.count}</td>
												<td>${module.moduleName}</td>
												<td>${module.canCreate}</td>
												<td>${module.canUpdate}</td>
												<td>${module.canDelete}</td>
												<td>${module.canView}</td>
											</tr>
										</c:forEach>
									</tbody>
								</c:forEach>
							</c:if>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.ROLE_ADD_URL%>" class="list-group-item">Add
						New Role</a><a href="<%=RealConstants.ROLE_ALL_VIEW%>"
						class="list-group-item">View Roles</a>
				</div>
			</div>
		</div>

	</div>
</body>
</html>