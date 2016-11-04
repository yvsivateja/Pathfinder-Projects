<!DOCTYPE html>
<%@page import="com.pathfinder.constants.RealConstants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduler Log</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>


</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-15 col-sm-8">
				<div class="col-sm-11 main">
					<h5>List of schedulers</h5>
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
									<th>Job Name</th>
									<th>Start Fire Time</th>
									<th>Previous Fire Time</th>
									<th>Next Fire Time</th>
								</tr>
							</thead>
							<tbody id="resultbody">
								<c:if test="${schedulerDTOs ne null}">
									<c:forEach var="schedulerDTO" items="${schedulerDTOs}"
										varStatus="serial">
										<tr>
											<td>${serial.count}</td>
											<td>${schedulerDTO.jobName}</td>
											<td>${schedulerDTO.startFireTime}</td>
											<td>${schedulerDTO.previousFireTime}</td>
											<td>${schedulerDTO.nextFireTime}</td>
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