<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<title>mangerCars</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/sitestyle.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
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
				<a class="navbar-brand" href="managerHome.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="managerHome.jsp"><fmt:message key="Main_Page" /></a></li>
				<li><a href="managerOrders.jsp"><fmt:message
							key="Manage_orders" /></a></li>
				<li class="active"><a href="managerCars.jsp"><fmt:message
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
	<div class="container" align="left">
			<div class="input-group">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-search"></i></span>  <input type="text"
					class="form-control" id="search" onkeyup="tableSearch()"
					placeholder="Search in table... ">
			</div>	
		<table class="table table-bordered" id="layOutTable">

			<tr>
				<th>Id</th>
				<th><fmt:message key="Carname" /></th>
				<th><fmt:message key="Mark" /></th>
				<th><fmt:message key="Class" /></th>
				<th><fmt:message key="Price" /></th>
				<th>action</th>
			</tr>

			<c:forEach var="car" items="${CARS_LIST}">
				<c:url var="updateLink" value="/CarController">
					<c:param name="command" value="Load" />
					<c:param name="carId" value="${car.id}" />
				</c:url>
				<c:url var="deleteLink" value="/CarController">
					<c:param name="command" value="Delete" />
					<c:param name="carId" value="${car.id}" />
				</c:url>
				<tr>
					<td>${car.id }</td>
					<td>${car.model}</td>
					<td>${car.mark}</td>
					<td>${car.carClass}</td>
					<td>${car.cost}</td>
					<td>
						<div class="btn-group">
							<a href="${updateLink}" class="btn btn-primary"> <i
								class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
								Update
							</a> <a href="${deleteLink}" class="btn btn-danger"
								onclick="if(!(confirm('Are you sure you want to delete this car'))) return false">
								<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
								Delete
							</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<h1>Add a new car</h1>
		<form class="form-inline" action="CarController" method="post">
			<div class="form-group">
				<label for="carName"><fmt:message key="Carname" /> </label><br>
				<input type="text" class="form-control" pattern="^\\w+$"
					maxLength="20" name="carName" id="carName"
					title="Only letters,numbers and signs ">
			</div>
			<div class="form-group">
				<label for="mark"><fmt:message key="Mark" /> </label><br> <input
					type="text" class="form-control" pattern="^[a-zA-Z]+$"
					maxLength="20" name="mark" id="mark" title="Only letters ">
			</div>

			<div class="form-group">
				<label for="mark"><fmt:message key="Price" /> </label><br> <input
					type="text" class="form-control" pattern="^[0-9]+$" maxLength="6"
					name="mark" id="mark" title="Only numbers ">
			</div>
			<div class="form-group">
				<label for="carClass"><fmt:message key="Class" /></label><br>
				<select class="form-control" id="carClass" name="carClass">
					<option>sedan</option>
					<option>miniven</option>
					<option>crossover</option>
					<option>hatchback</option>
				</select>
			</div>
			<br>
			<div class="input-group">
				<button type="submit" class="btn btn-primary" name="command"
					value="Add">
					<span class="glyphicon glyphicon-save"><fmt:message
							key="Add" /></span>
				</button>
			</div>
		</form>
	</div>
	<script src="js/tableSearch.js"></script>
<body>
</body>
</html>