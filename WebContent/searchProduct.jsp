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
<title>Search Product</title>
</head>
<body>
<%@ include file="header.jsp" %>  
<div align="center" style="padding-top:25px;">
	<form action="Product?val=searchProduct" method="Post">
		<label>Enter Product ID: </label>
		<input type="text" name="prodId" size="25" class="searchTextField"/>
		<button class="actionBtn">Search</button>
	</form>
</div> 
	<table align="center" class="productTable">
		<thead>
			<tr>
				
				<th>Product Name</th>
				<th>Category</th>
				<th>Price</th>
				<th colspan="2">Actions</th>
			</tr> 
		</thead>
		<c:set var="product" value="${product}" scope="session"/>
		<c:choose>
		<c:when test="${not empty product }">
		<tr>
				
				<td>${product.prod_name}</td>
				<td>${product.prod_category}</td>
				<td>${product.prod_price}</td>
				<td><button class="actionBtn"
					onclick="location.href = 'Product?prodId=${product.prod_id}&val=getEditProduct';">Edit</button></td>
			<td><button class="actionBtn"
					onclick="location.href = 'Product?prodId=${product.prod_id}&val=deleteProduct';">Delete</button></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:set var="message" value="${message}" scope="session"/>
		
		<tr>
				<c:if test="${not empty message}">
				<td colspan="5"><c:out value="${message}"></c:out></td>
				</c:if>
				<c:remove var="message" scope="session"/>
				
		</tr>
		</c:otherwise>
		</c:choose>	
		<c:remove var="product" scope="session"/>
	</table>

</body>
</html>
</c:otherwise>
</c:choose>