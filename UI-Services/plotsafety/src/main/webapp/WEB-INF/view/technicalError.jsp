<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Technical Error</title>

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
									<h4 class="title">Technical Error</h4>
								</div>
								<div class="content">
									<div class="alert alert-danger">
										<strong>OOPS!!!</strong> Something went wrong.
									</div>
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