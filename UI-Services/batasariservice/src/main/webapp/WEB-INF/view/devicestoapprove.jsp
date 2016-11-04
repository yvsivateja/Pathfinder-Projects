<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Devices Approval</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<link href="css/dataTables.bootstrap.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#formaction").hide();
		$("#error").hide();
		$("#resultbody tr").click(function() {
			/* $("#resultbody tr").removeClass("danger");
			$(this).addClass("danger"); */
			$("#formaction").show("fade");
		});
		$('#resultbody').on('click', 'tr', function() {
			$(this).toggleClass('danger');
		});

		$("#bootstrap_table").dataTable({
			"scrollCollapse" : true,
			"scrollY" : "270px"
		});
	});

	function isBlank(str) {
		return (!str || /^\s*$/.test(str));
	}

	function setSelectedRecords() {
		var selectedRows = $(".danger");
		var hiddenKeyValue = "";
		$(selectedRows).each(
				function(index, element) {

					var row = $(this).children('td').eq(0).text();
					if (isBlank(hiddenKeyValue)) {
						hiddenKeyValue = $("#device_key_" + row).val();
					} else {
						hiddenKeyValue = hiddenKeyValue + ","
								+ $("#device_key_" + row).val();
					}
				});
		//alert(hiddenKeyValue);
		if(isBlank(hiddenKeyValue)){
			$("#error").show("fade");
			return false;
		}
		$("#hidden_key").val(hiddenKeyValue);
		return true;
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
					<h5>List of devices to approve</h5>
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
									<th>S. No.</th>
									<th>Device User Identifier</th>
									<th>Device Name</th>
									<th>Phone Number</th>
									<th>Company Identifier</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${deviceDTOs ne null}">
									<c:forEach var="deviceDTO" items="${deviceDTOs}"
										varStatus="serial">
										<tr>
											<td>${serial.count}<input type="hidden"
												value="${deviceDTO.id}" id="device_key_${serial.count}" /></td>
											<td>${deviceDTO.userIdentifier}</td>
											<td>${deviceDTO.deviceName}</td>
											<td>${deviceDTO.phoneNumber}</td>
											<td>${deviceDTO.companyShortKey}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>

				<div class="col-sm-11 main">
					<form:form method="POST" commandName="deviceDTO"
						action="<%=RealConstants.APPROVE_DEVICES%>" role="form"
						id="formaction" onsubmit="return setSelectedRecords();">
						<form:hidden path="deviceID" id="hidden_key" />
						<div class="row">
							&nbsp;<span id="error" style="color: red">Select atleast
								one record</span>
						</div>
						<div class="col-sm-3">&nbsp;</div>
						<div class="form-group form-group-sm">
							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="approve">Approve</button>
						</div>

					</form:form>
				</div>


			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.EMPLOYEE_ADD_URL%>" class="list-group-item">Add
						New Employee</a> <a href="<%=RealConstants.EMPLOYEE_ALL_VIEW%>"
						class="list-group-item">View Employees</a>
				</div>
			</div>
		</div>


	</div>
	<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
</body>
</html>