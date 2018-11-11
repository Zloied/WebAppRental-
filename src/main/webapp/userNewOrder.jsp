<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>New order</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/relatTable.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/fixedTable.js"></script>
<jsp:include page="/CarController" />
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
				<li class="active"><a href="userNewOrder.jsp"><fmt:message
							key="New_order" /></a></li>
				<li><a href="userView.jsp"><fmt:message key="My_Orders" /></a></li>
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

	<div align="center">
		<form class="inputs">
			<div class="input-group">
				<div class="input-group-addon">
					<span class="glyphicon glyphicon-search"></span>
				</div>
				<input type="text" class="form-control" id="search"
					onkeyup="tableSearch()" placeholder="Search for car... ">
			</div>
		</form>

		<form action="OrderController" method="post">
			<table class="table-bordered" id="layOutTable">
				<tr>
					<th><fmt:message key="Carname" /></th>
					<th><fmt:message key="Mark" /></th>
					<th><fmt:message key="Class" /></th>
					<th><fmt:message key="Price" /></th>
					<th></th>
				</tr>
				<c:forEach var="car" items="${CARS_LIST}">
					<tr>
						<td>${car.model}</td>
						<td>${car.mark}</td>
						<td>${car.carClass}</td>
						<td>${car.cost}</td>
						<td><input type="radio" name="carId" id="carId" value="${car.id}"
							checked /></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<label><fmt:message key="With_driver" /></label>
			<div class="checkbox-inline">
				<label><input type="checkbox" name="driver" value="yes">
				<fmt:message key="Yes" /></label>
			</div>
			<div class="form-group">

				<label for="emailInput"><fmt:message key="From" /></label>
				<div class="input-group">
					<input type="date" class="form-control" name="startDate"
						id="startDate"> 
					<label for="emailInput"><fmt:message
							key="To" /></label>
					<input type="date" class="form-control"
						name="endDate" id="endDate">
				</div>
			</div>
			<div class="form-group" class="center">
				<button type="submit" name="command" value="Add"
					class="btn btn-primary">
					<fmt:message key="Proceed_with_payment" />
				</button>
				<button type="submit" name="command" value="Add"
					class="btn btn-primary">
					<fmt:message key="Proceed_without_payment" />
				</button>
			</div>

		</form>
	</div>
	<script src="js/dateCheck.js"></script>
	<script src="js/tableSearch.js"></script>
</body>
</html>