<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${theLocale}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="stud.jsp?theLocale=en">Eng</a>
	 |
	<a href="stud.jsp?theLocale=ru">Ru</a>
	<fmt:message key="Home" /><br />
	<fmt:message key="Login" /><i> John</i><br />
	<fmt:message key="Name" /><i> Die</i>
	<br />
</body>
</html>