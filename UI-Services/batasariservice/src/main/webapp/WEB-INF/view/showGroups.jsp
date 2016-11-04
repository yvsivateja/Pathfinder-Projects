<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registerd Companies</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<link href="css/dataTables.bootstrap.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#formaction").hide();
		$("#resultbody tr").click(function() {
			$("#resultbody tr").removeClass("danger");
			$(this).addClass("danger");
			$("#formaction").show("fade");
		});
		$("#bootstrap_table").dataTable( {
			"scrollCollapse": true,
			"scrollY" : "270px"
		} );
	});
	function setGroup(value) {
		$("#group_id").val(value);
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
					<h5>Click on the company to perform actions</h5>
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
									<th>Company</th>
									<th>Address</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${GroupsResultDTO.groupRegistrationDTOs ne null}">
									<c:forEach var="groupRegistrationDTO"
										items="${GroupsResultDTO.groupRegistrationDTOs}">
										<tr onclick="setGroup(${groupRegistrationDTO.groupid})">
											<td>${groupRegistrationDTO.groupName}</td>
											<td>${groupRegistrationDTO.address1},${groupRegistrationDTO.address2},${groupRegistrationDTO.address3},${groupRegistrationDTO.state},${groupRegistrationDTO.country}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-11 main">
					<form:form method="POST" commandName="groupRegistrationDTO"
						action="<%=RealConstants.GROUP_MANAGE_ACTIONS%>" role="form"
						id="formaction">
						<form:hidden path="groupid" id="group_id" />
						<div class="row">&nbsp;</div>
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="update">Update</button>
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="delete">Delete</button>
							<c:if test="${GroupsResultDTO.showImpersonate eq true}">
								<button class="btn btn-primary" type="submit"
									style="margin-right: 10%;" name="actionType"
									value="impersonate">Impersonate Company</button>
							</c:if>
						</div>

					</form:form>
				</div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.GROUP_ADD_URL%>" class="list-group-item">Add
						New Company</a> <a href="<%=RealConstants.GROUPS_ALL_VIEW%>"
						class="list-group-item">View Companies</a>
				</div>
			</div>
		</div>


	</div>
	<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
</body>
</html>