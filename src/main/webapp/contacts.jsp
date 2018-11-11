<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>Contacts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: url("images/contacts.jpg");
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
			<li><a href="registration.jsp"><fmt:message key="Sign_up" /></a></li>
			<li><a href="about.jsp"><fmt:message key="About" /></a></li>
			<li class="active"><a href="contacts.jsp"><fmt:message
						key="Contacts" /></a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
				<li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span>
						<fmt:message key="Sign_up" /></a></li>
				<li><a href="loginPage.jsp"><span class="glyphicon glyphicon-log-in"></span>
					<fmt:message key="SignIn" /></a></li>
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
	<div class="container" align="center">
		<h1>Our Work schedule</h1>
		<p>...</p>
	</div>	
	<div class="footer">
		<p>Telephone number : +421 908 375861</p>
		<p>Email: zloied11@gmail.com</p>
	</div>
</body>
</html>