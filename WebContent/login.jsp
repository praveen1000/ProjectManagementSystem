
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>PMS Login</title>
</head>
<body>
	<div align="center">
		<c:set var="message" value="${message}" scope="session"></c:set>
		<h3>${message}</h3>
		<c:remove var="message" scope="session"/>
		<%-- Another way to do the same thing with the help of if tag
		<%
			String message = (String) request.getAttribute("message");
		%>
		<c:if test="${not empty message}">
			<h2>${message}</h2>
		</c:if> --%>

	</div>
	<div align="center">


		<form action="User" method="post">
			<table class="loginForm">
				<tr>
					<td><label for="userName">User Name</label></td>
					<td><input type="text" id="userName" name="userName"
						class="searchTextField" /></td>
				</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" id="password" name="password"
						class="searchTextField" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Login" class="actionBtn" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>