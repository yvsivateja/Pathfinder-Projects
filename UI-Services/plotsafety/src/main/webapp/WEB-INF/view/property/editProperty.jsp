<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Edit Customer</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@include file="/WEB-INF/view/property/property-sidebar.jsp"%>
		<div class="main-panel">
			<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Update Property</h4>
								</div>
								<div class="content">
									<form
										action="addProperty.htm?${_csrf.parameterName}=${_csrf.token}"
										method="post" enctype="multipart/form-data">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label>Venture Name</label> <input type="text" required
														name="ventureName" class="form-control"
														placeholder="Venture Name" value="${property.ventureName}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Plot Number</label> <input type="text" name="plotNo"
														class="form-control" placeholder="Plot Number"
														value="${property.plotNo}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label>Survey Number</label> <input type="text"
														name="surveyNo" class="form-control"
														placeholder="Survey Number" value="${property.surveyNo}">
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label>Plot Area</label> <input type="text" name="plotArea"
														class="form-control" placeholder="Enter Plot Area"
														value="${property.plotArea}">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label>Plot Facing</label> <input type="text" name="facing"
														class="form-control" placeholder="Enter Plot Facing"
														value="${property.facing}">
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-group">
													<label>Audit Done</label>
													<c:if test="${property.audit eq '0' or property.audit eq null}">
														<input type="checkbox" name="audit" class="form-control">
													</c:if>
													<c:if test="${property.audit eq '1'}">
														<input type="checkbox" name="audit" class="form-control"
															checked>
													</c:if>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label>Landmark</label> <input type="text" name="landmark"
														class="form-control" placeholder="Enter Landmark here"
														value="${property.landmark}">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Market Value</label> <input type="text"
														name="marketValue" class="form-control"
														placeholder="Enter Market Value"
														value="${property.marketValue}">
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<label>Government Value</label> <input type="text"
														name="govtValue" class="form-control"
														placeholder="Enter Government Value"
														value="${property.govtValue}">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Transactions</label>
													<textarea placeholder="Enter Transactions Here.."
														name="transactions" rows="3" class="form-control">${property.transactions}</textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Developments</label>
													<textarea placeholder="Enter Any Recent Developments Here"
														name="developments" rows="3" class="form-control">${property.developments}</textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>Property Images</label>
													<div class="row">
														<div class="col-md-3">
															<span class="btn btn-fill btn-file btn-sm"
																style="overflow: inherit"> Browse <input
																type="file" name="files" accept="gif|jpg|png|tiff"
																id="selectFile">
															</span>
														</div>
														<div class="col-md-8">
															<c:forEach var="propertyImages"
																items="${property.propertyImagePOs}"
																varStatus="rowIndex">
																<div class="" rowid="${propertyImages.propertyImageID}">
																	<a class="filesDelete" href="#">x</a> <span class="">${propertyImages.imageName}</span>
																</div>
															</c:forEach>
															<input type="hidden" name="deletedFiles"
																id="deletedFiles" value="" /> <span class=""
																id="selectedFiles"></span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<input type="hidden" name="propertyID"
											value="${property.propertyID}" /> <input type="hidden"
											name="customerID" value="${property.customerID}" />
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