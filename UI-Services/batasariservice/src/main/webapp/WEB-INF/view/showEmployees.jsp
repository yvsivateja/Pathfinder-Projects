<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registerd Employees</title>
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
		$("#bootstrap_table").dataTable({
			"scrollCollapse": true,
			"scrollY" : "270px"
		});
		

	});
	
	function confirmationForDelete() {
	   var r = confirm("This operation cannot be reverted! \n Are you sure?");
	    if (r == true) {
	       	return true;
	    } else {
			return false;
	    }
	}	    
	function setEmployee(value) {
		$("#hidden_key").val(value);
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
					<h5>Click on the Employee to perform actions</h5>
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
									<th>Employee Name</th>
									<th>Age</th>
									<th>Sex</th>
									<th>Phone</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if
									test="${EmployeesResultDTO.employeeRegistrationDTO ne null}">
									<c:forEach var="employeeRegistrationDTO"
										items="${EmployeesResultDTO.employeeRegistrationDTO}">
										<tr
											onclick="setEmployee(${employeeRegistrationDTO.employeeId})">
											<td>${employeeRegistrationDTO.empName}</td>
											<td>${employeeRegistrationDTO.age}</td>
											<td>${employeeRegistrationDTO.sex}</td>
											<td>${employeeRegistrationDTO.empPhone}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-11 main">
					<form:form method="POST" commandName="employeeRegistrationDTO"
						action="<%=RealConstants.EMPLOYEE_MANAGE_ACTIONS%>" role="form"
						id="formaction">
						<form:hidden path="employeeId" id="hidden_key" />
						<div class="row">&nbsp;</div>
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="update">Update</button>
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="delete"
								onclick="return confirmationForDelete()">Delete</button>
						</div>

					</form:form>
				</div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.EMPLOYEE_ADD_URL%>" class="list-group-item">Add
						New Employee</a><a href="<%=RealConstants.EMPLOYEE_ALL_VIEW%>"
						class="list-group-item">View Employee</a>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
	</div>
</body>
</html>