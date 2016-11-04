<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>View All Customer</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/view/customer/customer-sidebar.jsp"%>
		<div class="main-panel">
			<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Customers</h4>
								</div>
								<div class="content table-responsive table-full-width">
									<table class="table table-hover table-striped" id="customer">
										<thead>
											<th>Customer Name</th>
											<th>Contact Number</th>
											<th>Customer Identifier</th>
											<th>Properties No#</th>
											<th class="no-sort"></th>
										</thead>
										<tbody>
											<c:if test="${customerProperties ne null}">
												<c:forEach var="customer" items="${customerProperties}"
													varStatus="rowIndex">
													<tr>
														<td>${customer.name}</td>
														<td>${customer.primaryNumber}</td>
														<td>${customer.identifier}</td>
														<td><c:if test="${customer.totalCount eq null}">0</c:if>${customer.totalCount}</td>
														<td class="links-small"><a
															href="viewProperties.htm?customerid=${customer.customerID}"
															class="">View</a> | <a
															href="deleteCustomer.htm?customerid=${customer.customerID}"
															class="">Delete</a> | <a href="#"
															onclick="setCustomerId(${customer.customerID})">Add
																Property</a> | <a
															href="customerPropertyReport.htm?customerid=${customer.customerID}&identifier=${customer.identifier}"
															class="opendialog" target="_blank">Download Report</a></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@include file="/WEB-INF/view/commonfiles/lazyimports.jsp"%>
<link rel="stylesheet" href="assets/css/dataTables.bootstrap.min.css">
<script type="text/javascript" src="assets/js/datatables.min.js"></script>
<%@include file="/WEB-INF/view/property/addPropertyModal.jsp"%>
<script type="text/javascript">
	function setCustomerId(customerid) {
		var elem = document.getElementById("customerid");
		elem.value = customerid;
		$("#addProperty").modal();
	}
	
	$('#customer').DataTable({
		"columnDefs" : [ {
			"targets" : 'no-sort',
			"orderable" : false
		} ],
	"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    "iDisplayLength": 8
	}); 
</script>

</html>