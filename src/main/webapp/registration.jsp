<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<style type="text/css">
body {
	background-image: url("images/blur.jpg");
}
</style>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="home.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="loginPage.jsp"><fmt:message key="SignIn" /></a></li>
				<li class="active"><a href="registration.jsp"><fmt:message
							key="Sign_up" /></a></li>
				<li><a href="about.jsp"><fmt:message key="About" /></a></li>
				<li><a href="contacts.jsp"><fmt:message key="Contacts" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="registration.jsp"><span
						class="glyphicon glyphicon-user"></span> <fmt:message
							key="Sign_up" /></a></li>
				<li><a href="loginPage.jsp"><span
						class="glyphicon glyphicon-log-in"></span> <fmt:message
							key="SignIn" /></a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><img class="img"
						src="images/RU.png"><img class="img" src="images/EN.png">
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="home.jsp?theLocale=ru"><img class="img"
								src="images/RU.png"></img></a></li>
						<li><a href="home.jsp?theLocale=en"><img class="img"
								src="images/EN.png"></a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div align="center">
		<form action="UserController" method="post"
			onsubmit="return checkForm(this)">
			<div class="media-left">
				<img src="images/avatar2.png" class="media-object"
					style="width: 60px">
			</div>

			<div class="form-group">
				<label for="login"><fmt:message key="Login" /></label> <br> <input
					type="text" class="form-control" pattern="[a-zA-Z]+" name="login"
					required maxlength="30" placeholder="Login"
					title="Only letters,numbers and signs ">
			</div>
			<div class="form-group">
				<label for="mail">Email</label> <br> <input type="email"
					class="form-control" name="email" required placeholder="@email">
			</div>
			<div class="form-group">
				<label for="password1"><fmt:message key="Password" /></label> <br>
				<input type="password" class="form-control" pattern="^[0-9a-zA-Z]+$"
					name="password1" maxlength="30" required placeholder="Password"
					title="Only letters and numbers ">
			</div>
			<div class="form-group">
				<label for="password2"><fmt:message key="Password" /></label> <br>
				<input type="password" class="form-control" pattern="^[0-9a-zA-Z]+$"
					name="password2" maxlength="30" required placeholder="Password"
					title="Only letters and numbers ">
			</div>
			<button type="submit" class="btn btn-primary" name="command"
				value="Registration">
				<fmt:message key="Sign_up" />
			</button>

			<p>${msg}
			<p>
		</form>
	</div>
	<script src="js/loginvalidation.js"></script>
</body>
</html>