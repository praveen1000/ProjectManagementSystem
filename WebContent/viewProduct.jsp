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
<title>Product Details</title>
</head>
<!-- <%@ include file="header.jsp" %>   -->
<jsp:include page="header.jsp" />
<table align="center" class="productTable">
	<thead>
		<tr>

			<th>Product Name</th>
			<th>Category</th>
			<th>Price</th>
			<th colspan="2">Actions</th>
		</tr>
	</thead>
	<c:forEach var="product" items="${productList}">

		<tr>

			<td>${product.prod_name}</td>
			<td>${product.prod_category}</td>
			<td>${product.prod_price}</td>
			<td><button class="actionBtn"
					onclick="location.href = 'Product?prodId=${product.prod_id}&val=getEditProduct';">Edit</button></td>
			<td><button class="actionBtn"
					onclick="location.href = 'Product?prodId=${product.prod_id}&val=deleteProduct';">Delete</button></td>
		</tr>
	</c:forEach>

</table>

</body>
		</html>
	</c:otherwise>
</c:choose>