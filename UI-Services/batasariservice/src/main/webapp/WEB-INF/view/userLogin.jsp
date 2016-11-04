<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    

</head>
<body>
	<div class="container">
		<form class="form-signin" action="j_spring_security_check"
			method="Post" name="loginform">
			<h2 class="form-signin-heading">Sign in</h2>
			<input type="text" name="username" class="form-control"
				placeholder="User Name" required autofocus> <input
				type="password" class="form-control" name="password"
				placeholder="Password" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

	</div>
	<!-- /container -->
	<%@include file="/WEB-INF/view/commonfiles/footer.jsp"%>

</body>
</html>