<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<c:set var="theLocale"
	value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale }"
	scope="session" />
<fmt:setLocale value="${theLocale}" />
<fmt:bundle basename="i18n.resources" />
<html>
<head>
<title>Main page</title>
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/bootstrap.css">
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
				<a class="navbar-brand" href="userHome.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="userNewOrder.jsp"><fmt:message key="New_order" /></a></li>
				<li><a href="userView.jsp"><fmt:message key="My_Orders" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LogOutServ"> <span
						class="glyphicon glyphicon-log-out"></span> <fmt:message
							key="LogOut" />
				</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><img class="img"
						src="images/RU.png"><img class="img" src="images/EN.png">
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="userHome.jsp?theLocale=ru"><img class="img"
								src="images/RU.png"></img></a></li>
						<li><a href="userHome.jsp?theLocale=en"><img class="img"
								src="images/EN.png"></a></li>
					</ul></li>
			</ul>
		</div>
	</nav>

	<p class="conf">${Message}</p>
	<div align="center">
		<div class="btn-group">
			<a href="userNewOrder.jsp" class="btn btn-info"> <fmt:message key="New_order" />
			</a> <a href="userView.jsp" class="btn btn-info"> <fmt:message key="My_Orders" /> </a> <a
				href="#" class="btn btn-info"> Leave feedback </a>
		</div>
	</div>
</body>
</html>