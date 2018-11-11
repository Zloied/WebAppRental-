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
<title>Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap-3.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<head>
<style>
.carousel-inner>.item>img {
	width: 100%;
	height: 800px;
}
</style>
</head>
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
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">

				<div class="item active">
					<img src="images/car_1.jpg" alt="Clients">
					<div class="carousel-caption">
						<h1>Clients</h1>
						<p class="lead">We take care about our clients,so we have some
							bonuses for new clients and for old ones too.</p>
						<a class="btn btn-lg btn-primary" href="registration.jsp">Sign
							up today</a>
					</div>
				</div>

				<div class="item">
					<img src="images/car_2.jpg" alt="Company">
					<div class="carousel-caption">
						<h1>Company</h1>
						<p class="lead">Our company have been providing services about
							20 years.</p>
						<a class="btn btn-lg btn-primary" href="about.jsp">About</a>
					</div>
				</div>

				<div class="item">
					<img src="images/car_3.jpg" alt="Cars">
					<div class="carousel-caption">
						<h1>Cars</h1>
						<p class="lead">Everyone can get car he wants,so don't waste
							the time and make an order.</p>
						<a class="btn btn-lg btn-primary" href="loginPage.jsp">SignIn</a>
					</div>
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>

</body>
</html>