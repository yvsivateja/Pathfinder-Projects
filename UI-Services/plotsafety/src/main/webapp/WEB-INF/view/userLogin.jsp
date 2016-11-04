<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Plot Safety Admin Login</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/login-styles.css" rel="stylesheet" />
<link href="assets/css/login-font.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<section id="logo">
		<a href="http://www.plotsafety.com/"><img
			src="assets/img/company-logo.jpg" alt="" /></a>
	</section>

	<section class="container">
		<section class="row">
			<form method="post" action="j_spring_security_check" role="login">
				<div class="form-group">
					<input type="text" name="username"
						placeholder="Enter your username" autofocus class="form-control" />
					<span class="glyphicon glyphicon-user"></span>
				</div>

				<div class="form-group">
					<input type="password" name="password" placeholder="Enter password"
						required class="form-control" /> <span
						class="glyphicon glyphicon-lock"></span>
				</div>

				<!-- <div class="form-group">
					<input type="checkbox" name="remember" value="1" /> Remember me
				</div> -->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button type="submit" name="go" class="btn btn-block btn-primary">Login
					Now</button>

				<!-- <section>
					<a href="#">Forgot your password ?</a>
				</section> -->
			</form>
		</section>
	</section>
	<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>