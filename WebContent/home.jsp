
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
<title>Home</title>
</head>
<body>
	<!-- page is included at translational time -->
	<!--  <%@ include file="header.jsp" %>-->
	<!-- page is included at runtime -->
	<jsp:include page="header.jsp" />
	<%-- <%
String userName = (String)session.getAttribute("userName");
%> --%>
	<div align="center">
		<h2>Product Management System</h2>
		<%-- <label>Welcome <%= userName.toUpperCase() %></label> --%>
		<label>Welcome ${name}</label>


	</div>

</body>
		</html>
	</c:otherwise>
</c:choose>