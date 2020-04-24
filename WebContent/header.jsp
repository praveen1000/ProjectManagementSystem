<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="name" value="${userName}" />
<c:choose>
	<c:when test="${empty name}">
		<%
			response.sendRedirect("login.jsp");
		%>
	</c:when>
	<c:otherwise>
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
			pageEncoding="ISO-8859-1"%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
</head>
<body>

	<nav class="navbar">
		<ul class="navbar-nav">
			<li><a href="home.jsp">Home</a></li>
			<li><a href="addProduct.jsp">Add Product</a></li>
			<li><a href="Product?val=viewProduct">View Products</a></li>
			<li><a href="searchProduct.jsp">Search Product</a></li>
			<li style="float: right; margin-right: 10px"><a href="User?val=logout">Logout</a></li>
		</ul>
	</nav>
</body>
		</html>
	</c:otherwise>
</c:choose>