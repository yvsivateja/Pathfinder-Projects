<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Denied</title>
<%@include file="/WEB-INF/view/commonfiles/headerimports.jsp"%>

</head>
<body>
	<div class="container">
		<%@include file="/WEB-INF/view/commonfiles/navigation.jsp"%>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-20 col-sm-14">
				<div class="col-sm-12 main">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<h3 class="panel-title">Access Forbidden</h3>
						</div>
						<div class="panel-body">
							<strong> Sorry!</strong> Page cannot be accessed. Contact your
							administrator for privileges
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>
</body>
</html>