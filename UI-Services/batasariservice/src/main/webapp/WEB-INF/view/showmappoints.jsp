<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Device Map Points</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
<link href="css/dataTables.bootstrap.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<script src="js/jquery.dataTables.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script src="js/initializemappoints.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script type="text/javascript">
	$(document).ready(function() {
		google.maps.event.addDomListener(window, 'load', initialize);
		$('#datetimepicker1').datepicker({
			format : "dd-mm-yyyy",
			autoclose : true,
			todayHighlight : true,
			todayBtn : true,
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>

		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-20 col-sm-9">
				<div class="col-sm-11 main">
					<div class="row" style="padding-bottom: 10px">
						<form action="<%=RealConstants.DEVICE_SHOW_LOCATIONS%>"
							method="get">
							<label for="dateFrom">Records Fetched for : </label><input
								id="datetimepicker1" name="ondate" type="text"
								value="<%=request.getAttribute("ondate")%>" class="input-xsmall"
								style="color: #000;" /> <input type="hidden" name="device"
								value="<%=request.getParameter("device")%>">
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
					<div id="map-canvas"></div>
				</div>
			</div>
			<c:set var="generatedText" value="" />
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<c:if test="${deviceLocationDTOs ne null}">
					<div class="list-group"
						style="height: auto; max-height: 448px; overflow-x: hidden;">
						<span class="list-group-item active">Map Points</span>
						<c:forEach var="deviceLocationsDTO" items="${deviceLocationDTOs}"
							varStatus="serialCount">

							<span class="list-group-item"><b>Point
									${serialCount.count} :</b> <BR /> ${deviceLocationsDTO.address}<BR />Distance
								Travelled : ${deviceLocationsDTO.distance}<BR /> Time :
								${deviceLocationsDTO.dateTriggerd} </span>
							<c:set var="currentText"
								value="${deviceLocationsDTO.latitude},${deviceLocationsDTO.longitude};" />
							<c:set var="generatedText" value="${generatedText}${currentText}" />
						</c:forEach>

					</div>
				</c:if>
				<c:if test="${empty deviceLocationDTOs}">
					<ul class="nav nav-sidebar">
						<li>No Points</li>
					</ul>
				</c:if>
			</div>
			<div style="display: none" id="hidden_text">${generatedText}</div>
		</div>
		<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
	</div>
</body>
</html>