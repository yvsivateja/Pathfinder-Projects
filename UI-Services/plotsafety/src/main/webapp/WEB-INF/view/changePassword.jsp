<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Change Password</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
</head>
<body>
	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="assets/img/sidebar-6.jpg">

			<!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->

			<div class="sidebar-wrapper">
				<div class="logo">
					<div class="row">
						<div class="col-md-4">
							<img src="assets/img/company-logo.jpg" alt="Plot Safety"
								style="max-height: 40px;">
						</div>
						<div class="col-md-8">
							<a href="http://www.plotsafety.com/" class="simple-text">
								Plot Saftey </a>
						</div>
					</div>
				</div>

				<ul class="nav">
					<li><a href="viewCustomers.htm"> <i
							class="glyphicon glyphicon-list-alt"></i>
							<p>View All Customers</p>
					</a></li>
					<li class=""><a href="addCustomer.htm"> <i
							class="glyphicon glyphicon-user"></i>
							<p>Add Customer</p>
					</a></li>
					<!-- <li class=""><a href="customerPropertyReport.htm"> <i
							class="glyphicon glyphicon-stats"></i>
							<p>Property Report</p>
					</a></li> -->
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Change Password</h4>
								</div>
								<div class="content">
									<c:if test="${status eq true}">
										<div class="alert alert-success">
											<strong>Password</strong> updated successfully
										</div>
									</c:if>
									<form class="form-inline" role="form"
										action="updatePassword.htm" method="POST">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label style="font-weight: bold;">Password:</label> <input
														type="password" name="newPassword" class="form-control"
														id="password" size="20" autofocus>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
												<button type="submit" class="btn btn-info btn-fill">Save</button>
												<button type="reset" class="btn btn-info btn-default">Reset</button>
												<div class="clearfix"></div>
											</div>
										</div>
									</form>

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
</html>