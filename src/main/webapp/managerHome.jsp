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
<title>Main</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/sitestyle.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<jsp:include page="/OrderController" />
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
				<a class="navbar-brand" href="managerHome.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="managerHome.jsp"><fmt:message
							key="Main_Page" /></a></li>
				<li><a href="managerOrders.jsp"><fmt:message
							key="Manage_orders" /></a></li>
				<li><a href="managerCars.jsp"><fmt:message
							key="Manage_cars" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
						<fmt:message key="LogOut" /></a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"><img class="img"
						src="images/RU.png"><img class="img" src="images/EN.png">
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="managerHome.jsp?theLocale=ru"><img
								class="img" src="images/RU.png"></img></a></li>
						<li><a href="managerHome.jsp?theLocale=en"><img
								class="img" src="images/EN.png"></a></li>
					</ul>
			</ul>
		</div>
	</nav>
	<div align="center">
		<div class="btn-group">
			<a href="managerOrders.jsp" class="btn btn-info"> <fmt:message
					key="Manage_orders" />
			</a> <a href="managerCars.jsp" class="btn btn-info"> <fmt:message
					key="Manage_cars" />
			</a>
		</div>
	</div>
</body>
</html>