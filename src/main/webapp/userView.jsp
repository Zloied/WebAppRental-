<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>My orders</title>
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/relatTable.css">
<link rel="stylesheet" href="css/bootstrap.css">
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
				<a class="navbar-brand" href="userHome.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="userNewOrder.jsp"><fmt:message key="New_order" /></a></li>
				<li class="active"><a href="userView.jsp"><fmt:message
							key="My_Orders" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LogOutServ"><span
						class="glyphicon glyphicon-log-out"></span> <fmt:message
							key="LogOut" /></a></li>
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
	<div class="center">
		<table>
			<tr>
				<th scope="col"><fmt:message key="OrderId" /></th>
				<th><fmt:message key="CarId" /></th>
				<th><fmt:message key="With_driver" /></th>
				<th><fmt:message key="Price" /></th>
				<th><fmt:message key="Status" /></th>
				<th><fmt:message key="Start_date" /></th>
				<th><fmt:message key="Final_date" /></th>


			</tr>

			<c:forEach var="order" items="${listOrders}">
				<tr>
					<td>${order.id}</td>
					<td>${order.carId}</td>
					<td>${order.driver}</td>
					<td>${order.bill}</td>
					<td>${order.status}</td>
					<td>${order.start_date}</td>
					<td>${order.finish_date }</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>