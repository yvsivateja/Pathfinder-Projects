<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Customer Properties</title>

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
						<div class="col-md-5">
							<div class="card">
								<div class="header">
									<h4 class="title">Customer Details</h4>
								</div>
								<div class="content">
									<c:if test="${customerProperties[0].propertyPOs ne null}">

										<dl class="dl-horizontal">
											<dt>Customer Name</dt>
											<dd>${customerProperties[0].name}</dd>
											<dt>Customer Identifier</dt>
											<dd>${customerProperties[0].identifier}</dd>
											<dt>Primary Number</dt>
											<dd>${customerProperties[0].primaryNumber}</dd>
											<dt>Secondary Number</dt>
											<dd>${customerProperties[0].secondaryNumber}</dd>
											<dt>Email</dt>
											<dd>${customerProperties[0].email}</dd>
											<dt>Address</dt>
											<dd>${customerProperties[0].address}</dd>
											<dt>Country</dt>
											<dd>${customerProperties[0].country}</dd>
											<dt>State</dt>
											<dd>${customerProperties[0].state}</dd>
											<dt>City</dt>
											<dd>${customerProperties[0].city}</dd>
											<dt>Age</dt>
											<dd>${customerProperties[0].age}</dd>
											<dt>Gender</dt>
											<dd>${customerProperties[0].gender}</dd>
											<dt>Additional Information</dt>
											<dd>${customerProperties[0].additionalInfo}</dd>
										</dl>
										<button type="button" class="btn btn-fill"
											onclick="window.location.href='editCustomer.htm?customerid=${customerProperties[0].customerID}'">Edit</button>
									</c:if>
									<c:if test="${customerProperties[0].propertyPOs eq null}">
										<div class="alert alert-warning">
											<span>Invalid Customer</span>
										</div>
										<c:redirect url="/" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-md-7">
							<div class="card">
								<div class="header">
									<span class="h4">Properties</span>

									<button type="submit" class="btn btn-fill btn-default"
										style="float: right;" data-toggle="modal"
										data-target="#addProperty">Add New Property</button>
								</div>
								<div class="content">
									<div class="panel-group" id="accordion">
										<c:if test="${customerProperties[0].propertyPOs ne null}">
											<c:forEach var="property"
												items="${customerProperties[0].propertyPOs}"
												varStatus="rowIndex">
												<!-- start -->
												<div class="panel panel-default">
													<div class="panel-heading clickable" data-toggle="collapse"
														data-parent="#accordion"
														data-target="#collapse-${rowIndex.count}">
														<h4 class="panel-title">Property - ${rowIndex.count}
														</h4>
													</div>
													<c:set var="defaulAccordOpen" value="in" />
													<c:if test="${rowIndex.count ne 1}">
														<c:set var="defaulAccordOpen" value="" />
													</c:if>
													<div id="collapse-${rowIndex.count}"
														class="panel-collapse collapse ${defaulAccordOpen}">
														<div class="panel-body">
															<dl class="dl-horizontal">
																<dt>Plot Number</dt>
																<dd>${property.plotNo}<a href="#"
																		class="pull-right" data-toggle="modal"
																		data-target="#priceVariation">Property Price
																		Variation</a>
																</dd>
																<dt>Survey Number</dt>
																<dd>${property.surveyNo}</dd>
																<dt>Venture Name</dt>
																<dd>${property.ventureName}</dd>
																<dt>Plot Area</dt>
																<dd>${property.plotArea}</dd>
																<dt>Facing</dt>
																<dd>${property.facing}</dd>
																<dt>Landmark</dt>
																<dd>${property.landmark}</dd>
																<dt>Market Value</dt>
																<dd>${property.marketValue}</dd>
																<dt>Govt Value</dt>
																<dd>${property.govtValue}</dd>
																<dt>Transactions</dt>
																<dd>${property.transactions}</dd>
																<dt>Developments</dt>
																<dd>${property.developments}</dd>
																<dt>Audit</dt>
																<dd>
																	<c:if
																		test="${property.audit eq 0 or property.audit eq null}">
																		<span class="text-red"><i
																			class="glyphicon glyphicon-remove"></i>Not Done</span>
																	</c:if>
																	<c:if test="${property.audit eq 1}">
																		<span class="text-green"><i
																			class="glyphicon glyphicon-ok"></i>Done</span>
																	</c:if>
																</dd>
															</dl>
															<div class="row thumbnail-images">
																<div class="col-sm-6 col-md-3">
																	<label>Property Images</label>
																</div>
																<c:if test="${empty property.propertyImagePOs}">
																	<div class="col-sm-6 col-md-3">
																		<label>No Images Available</label>
																	</div>
																</c:if>
																<c:if test="${not empty property.propertyImagePOs}">
																	<c:forEach var="propertyimage"
																		items="${property.propertyImagePOs}">
																		<div class="col-sm-6 col-md-3">
																			<a href="#" class="thumbnail"
																				title="${propertyimage.imageName}"> <img
																				src="data:image/jpeg;base64,${propertyimage.encodedImage}"
																				alt="Generic placeholder thumbnail">
																			</a>
																		</div>
																	</c:forEach>
																</c:if>
															</div>
															<div class="form-group">
																<div class="col-sm-offset-2 col-sm-10">
																	<a class="btn btn-fill"
																		href="editProperty.htm?propertyid=${property.propertyID}">Edit</a>
																	<a class="btn btn-fill"
																		href="deleteProperty.htm?propertyid=${property.propertyID}&customerid=${customerProperties[0].customerID}">Delete</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${empty customerProperties[0].propertyPOs}">
											<div class="alert alert-warning">
												<span>No Properties Registered for this Customer</span>
											</div>
										</c:if>
										<!-- end -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include
		file="/WEB-INF/view/commonfiles/lightbox-image-gallery-model.jsp"%>
</body>
<%@include file="/WEB-INF/view/commonfiles/lazyimports.jsp"%>
<%@include file="/WEB-INF/view/property/addPropertyModal.jsp"%>
<%@include file="/WEB-INF/view/property/propertyPriceVariation.jsp"%>
<script src="assets/js/waitdialogplugin.js"></script>

<script type="text/javascript">
	function getPropertyVariation(propertyId) {
		$("#priceVariation").modal();
	}
</script>

</html>