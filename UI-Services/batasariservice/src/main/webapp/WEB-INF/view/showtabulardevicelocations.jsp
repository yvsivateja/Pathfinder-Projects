<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Devices Tabular View</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<link href="css/dataTables.bootstrap.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#bootstrap_table").dataTable({
			"scrollCollapse" : true,
			"scrollY" : "270px"
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-15 col-sm-8">
				<div class="col-sm-11 main">
					<h5>Device Locations</h5>
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
									<th>Address</th>
									<th>Date/Time</th>
									<th>Distance</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${deviceLocationDTOs ne null}">
									<c:forEach var="deviceLocationDTO"
										items="${deviceLocationDTOs}">
										<tr>
											<td>${deviceLocationDTO.address}</td>
											<td>${deviceLocationDTO.dateTriggerd}</td>
											<td>${deviceLocationDTO.distance}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas">
				<div class="list-group">
					<span class="list-group-item active">Items</span> <a
						href="<%=RealConstants.USER_HOME%>" class="list-group-item">Home</a><a
						href="<%=RealConstants.DEVICES_VIEW_ALL%>" class="list-group-item">View
						Devices</a>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
	</div>
</body>
</html>