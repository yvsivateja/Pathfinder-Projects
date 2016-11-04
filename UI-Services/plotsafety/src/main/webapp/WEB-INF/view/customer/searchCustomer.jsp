<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Search Customer</title>

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
									<h4 class="title">Search Customer</h4>
								</div>
								<div class="content">
									<form action="saveCustomer.htm" method="post">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Customer Name</label> <input type="text"
														name="customerName" class="form-control"
														placeholder="Customer Name">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Email</label> <input type="email"
														name="customerName" class="form-control"
														placeholder="Email">
												</div>
											</div>
										</div>
										<div class="row"></div>

									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Add Customer</h4>
								</div>
								<div class="content"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<%@include file="/WEB-INF/view/commonfiles/lazyimports.jsp"%>
</html>