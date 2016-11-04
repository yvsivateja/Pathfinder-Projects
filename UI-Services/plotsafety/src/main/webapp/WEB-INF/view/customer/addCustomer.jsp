<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Add Customer</title>

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
									<h4 class="title">Add Customer</h4>
								</div>
								<div class="content">
									<form action="saveCustomer.htm" method="post">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Customer Name</label> <input type="text" required
														name="name" class="form-control"
														placeholder="Customer Name">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Age</label> <input type="number" name="age" required
														name="primaryNumber" class="form-control"
														placeholder="Enter Age Here" min="18" max="100">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Gender</label>
													<div class="">
														<label class="radio-inline"><input type="radio"
															required name="gender" value="M">Male</label> <label
															class="radio-inline"><input type="radio"
															name="gender" value="F">Female</label>
													</div>
												</div>
											</div>

										</div>

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label>Primary Contact Number</label> <input type="text"
														name="primaryNumber" class="form-control" required
														placeholder="Enter Primary Number">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Secondary Contact Number</label> <input type="text"
														name="secondaryNumber" class="form-control"
														placeholder="Enter Secondary Contact">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Email</label> <input type="email" name="email"
														required class="form-control"
														placeholder="Enter Email here">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label>Country</label> <input type="text" name="country"
														required class="form-control" placeholder="Enter Country">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>State</label> <input type="text" name="state"
														required class="form-control" placeholder="Enter State">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>City</label> <input type="text" name="city" required
														class="form-control" placeholder="Enter city">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Address</label>
													<textarea placeholder="Enter Address Here.." name="address"
														required rows="3" class="form-control"></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Additional Information</label>
													<textarea placeholder="Enter Any Additional Info Here"
														name="additionalInfo" rows="3" class="form-control"></textarea>
												</div>
											</div>
										</div>
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<button type="submit" class="btn btn-info btn-fill pull-right">Save</button>
										<button type="reset"
											class="btn btn-info btn-default pull-right">Reset</button>
										<div class="clearfix"></div>
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