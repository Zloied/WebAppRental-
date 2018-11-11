<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>Manage orders</title>
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
				<li><a href="managerHome.jsp"><fmt:message key="Main_Page" /></a></li>
				<li class="active"><a href="managerOrders.jsp"><fmt:message
							key="Manage_orders" /></a></li>
				<li><a href="managerCars.jsp"><fmt:message
							key="Manage_cars" /></a></li>
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
						<li><a href="managerHome.jsp?theLocale=ru"><img
								class="img" src="images/RU.png"></img></a></li>
						<li><a href="managerHome.jsp?theLocale=en"><img
								class="img" src="images/EN.png"></a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="container" align="center">

		<div class="input-group">
			<span class="input-group-addon"><i
				class="glyphicon glyphicon-search"></i></span> <input type="text"
				class="form-control" id="search" onkeyup="tableSearch()"
				placeholder="Search in table... ">
		</div>
		<table class="table table-striped" id="layOutTable">
			<thead class="thead-dark">
				<tr>
					<th scope="col"><fmt:message key="OrderId" /></th>
					<th scope="col"><fmt:message key="UserId" /></th>
					<th><fmt:message key="CarId" /></th>
					<th><fmt:message key="With_driver" /></th>
					<th><fmt:message key="Start_date" /></th>
					<th><fmt:message key="Final_date" /></th>
					<th><fmt:message key="Price" /></th>
					<th><fmt:message key="Status" /></th>
					<th><fmt:message key="Action" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${listOrders}">
					<c:url var="statusReq" value="/OrderController">
						<c:param name="command" value="ChangeStatus" />
						<c:param name="orderId" value="${order.id}" />
						<c:param name="setStatus" value="requested" />
					</c:url>
					<c:url var="statusProc" value="/OrderController">
						<c:param name="command" value="ChangeStatus" />
						<c:param name="orderId" value="${order.id}" />
						<c:param name="setStatus" value="proceeding" />
					</c:url>
					<c:url var="statusComp" value="/OrderController">
						<c:param name="command" value="ChangeStatus" />
						<c:param name="orderId" value="${order.id}" />
						<c:param name="setStatus" value="completed" />
					</c:url>
					<tr>
						<td scope="row">${order.id}</td>
						<td>${order.userId}</td>
						<td>${order.carId}</td>
						<td>${order.driver}</td>
						<td>${order.start_date}</td>
						<td>${order.finish_date}</td>
						<td>${order.bill}</td>
						<td>${order.status}</td>
						<td>
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown">
									<fmt:message key="SetStatus" />
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu"
									onclick="if(!(confirm('are you sure you want to change status?'))) return false">
									<li><a type="submit" href="${statusReq}">requested</a></li>
									<li><a type="submit" href="${statusProc}">proceeding</a></li>
									<li><a type="submit" href="${statusComp}">completed</a></li>
								</ul>
							</div>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="js/tableSearch.js"></script>
</body>
</html>