<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registered Devices</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<link href="css/dataTables.bootstrap.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#formaction").hide();
		$('#myModal').modal('hide');
		$("#resultbody tr").click(function() {
			$("#resultbody tr").removeClass("danger");
			$(this).addClass("danger");
			$("#formaction").show("fade");
			
			var row=$(this);
			$("#deviceName").html(row.find('td:eq(2)').html());
			$("#userIdentifier").html(row.find('td:eq(1)').html());
			
			var canStart = row.find('td:eq(3)').html();
			var canStop = row.find('td:eq(4)').html();
			var canUninstall = row.find('td:eq(5)').html();
			
			if (canStart == "true") {
				$(".canstart").attr('checked', true);
			}
			if (canStop == "true") {
				$(".canstop").attr('checked', true);
			}
			if (canUninstall == "true") {
				$(".canuninstall").attr('checked', true);
			}

		});
		$("#update").click(function() {
			$('#myModal').modal('show');
		});
		$(".canuninstall").click(function() {
			
			if($(this).is(":checked")){
				$(".canstart").prop('checked', false);
				$(".canstop").prop('checked', false);
			}
		});
		
		$(".functional").click(function() {
			$(".canuninstall").prop('checked', false);
		});
		$("#bootstrap_table").dataTable({
			"scrollCollapse" : true,
			"scrollY" : "270px"
		});

	});

	function confirmationForDelete() {
		var r = confirm("This action cannot be reverted! \n Are you sure?");
		if (r == true) {
			return true;
		} else {
			return false;
		}
	}
	function setDevice(value){
		$(".id").val(value);
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
					<h5>Click on the Device to perform actions</h5>
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
									<th>Device Identifier</th>
									<th>Device Name</th>
									<th>Can Start</th>
									<th>Can Stop</th>
									<th>Can Uninstall</th>
									<th>Track Device</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${deviceDTOs ne null}">
									<c:forEach var="deviceDTO" items="${deviceDTOs}"
										varStatus="serial">
										<tr onclick="setDevice(${deviceDTO.id})">
											<td>${serial.count}</td>
											<td>${deviceDTO.userIdentifier}</td>
											<td>${deviceDTO.deviceName}</td>
											<td>${deviceDTO.canStart}</td>
											<td>${deviceDTO.canStop}</td>
											<td>${deviceDTO.canUninstall}</td>
											<td><a
												href="<%=RealConstants.DEVICE_SHOW_LOCATIONS%>?device=${deviceDTO.deviceID}"><span
													class="glyphicon move-forward"></span></a></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-11 main">

					<div class="row">&nbsp;</div>
					<div class="col-sm-3">&nbsp;</div>
					<div class="form-group form-group-sm" id="formaction">
						<form:form method="POST" commandName="deviceDTO"
							action="<%=RealConstants.DEVICE_MANAGE_ACTION%>"
							class="form-horizontal" role="form">
							<form:hidden path="id" cssClass="form-control id"
								cssErrorClass="" maxlength="40" />
							<button class="btn btn-primary" type="button"
								style="margin-right: 10%;" name="actionType" value="update"
								id="update">Update</button>


							<button class="btn btn-primary" type="submit"
								style="margin-right: 10%;" name="actionType" value="delete"
								onclick="return confirmationForDelete()">Delete</button>
						</form:form>
					</div>

				</div>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<form:form method="POST" commandName="deviceDTO"
								action="<%=RealConstants.DEVICE_MANAGE_ACTION%>"
								class="form-horizontal" role="form">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Update Device</h4>
								</div>
								<div class="modal-body">
									<div class="form-group form-group-sm">
										<label class="col-sm-4 control-label">Device Name :</label> <span
											class="col-sm-4 control-label" id="deviceName"></span>
										<form:hidden path="id" cssClass="form-control id"
											cssErrorClass="" maxlength="40" />
									</div>
									<div class="form-group form-group-sm">
										<label class="col-sm-4 control-label">Device
											Identifier :</label> <span class="col-sm-4 control-label"
											id="userIdentifier"></span>
									</div>

									<div class="form-group form-group-sm">
										<div class="col-sm-3">&nbsp;</div>
										<div class="col-sm-9">
											<label class="checkbox-inline"> <form:checkbox
													path="canStart" id="inlineCheckbox1"
													class="canstart functional" /> Start
											</label> <label class="checkbox-inline"> <form:checkbox
													path="canStop" id="inlineCheckbox1"
													class="canstop functional" /> Stop
											</label> <label class="checkbox-inline"> <form:checkbox
													path="canUninstall" id="inlineCheckbox1"
													class="canuninstall" /> Uninstall
											</label>
										</div>
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary" name="actionType"
										value="update">Submit changes</button>
								</div>
							</form:form>
						</div>
						<!-- /.modal-content -->
					</div>

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