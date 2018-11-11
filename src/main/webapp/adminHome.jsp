<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<jsp:include page="/UserController" />
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
				<a class="navbar-brand" href="adminHome.jsp"><span
					class="glyphicon glyphicon-home"></span></a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="adminHome.jsp"><fmt:message
							key="Main_Page" /></a></li>
				<li><a href="adminAdd.jsp"><fmt:message key="Add_User" /></a></li>
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
						<li><a href="adminHome.jsp?theLocale=ru"><img class="img"
								src="images/RU.png"></img></a></li>
						<li><a href="adminHome.jsp?theLocale=en"><img class="img"
								src="images/EN.png"></a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<div class="center">
		<form class="form-inline">
			<div class="form-group">
				<span class="glyphicon glyphicon-search"></span> <input type="text"
					class="form-control" id="search" onkeyup="tableSearch()"
					placeholder="Search in table... ">
			</div>
		</form>
		<table class="table table-bordered" id="layOutTable">
			<tr>
				<th>Users Id</th>
				<th>user Name</th>
				<th>Role</th>
				<th>status</th>
				<th>email</th>
				<th>action</th>
			</tr>
			<c:forEach var="user" items="${listUsers}">
				<c:url var="deleteLink" value="/UserController">
					<c:param name="command" value="Delete" />
					<c:param name="usersId" value="${user.id}" />
				</c:url>
				<c:url var="statusConf" value="/UserController">
					<c:param name="command" value="ChangeStatus" />
					<c:param name="usersId" value="${user.id}" />
					<c:param name="setStatus" value="confirmed" />
				</c:url>
				<c:url var="statusUncon" value="/UserController">
					<c:param name="command" value="ChangeStatus" />
					<c:param name="usersId" value="${user.id}" />
					<c:param name="setStatus" value="unconfirmed" />
				</c:url>
				<c:url var="statusBlock" value="/UserController">
					<c:param name="command" value="ChangeStatus" />
					<c:param name="usersId" value="${user.id}" />	
					<c:param name="setStatus" value="blocked" />				
				</c:url>
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.role}</td>
					<td>${user.email}</td>
					<td>${user.status}</td>					
					<td>
						<div class="btn-group">

							<div class="btn-group">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown">
									Set status <span class="caret"></span>
								</button>
								<ul class="dropdown-menu"
									onclick="if(!(confirm('Are you sure you want to change status of this user')))return false">
									<li><a href="${statusConf}">confirmed</a></li>
									<li><a href="${statusUncon}">unconfirmed</a></li>
									<li><a href="${statusBlock}">blocked</a></li>
								</ul>
							</div>
							<a href="${deleteLink}" class="btn btn-danger"
								onclick="if(!(confirm('Are you sure you want to delete this user')))return false">
								<i class="glyphicon glyphicon-trash"></i> Delete
							</a>

						</div>
				</tr>

			</c:forEach>
		</table>
		<a href="adminAdd.jsp" class="btn btn-info"><i
			class="glyphicon glyphicon-user"></i> Add user</a>
	</div>
	<script src="js/tableSearch.js"></script>
</body>
</html>