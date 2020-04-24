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
<title>Edit Product</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<c:set var="product" value="${product}" />
	<div align="center">

		<form action="Product?val=postEditProduct&productId=${product.prod_id}" method="post">
			
			<!--  <input type ="hidden" name= "productId" value="">-->
			<table class="productTable">
				<thead>
					<tr>
						<th colspan="2">Product Details</th>
					</tr>
				</thead>

				<tr>
					<td>Product Name</td>
					<td><input type="text" name="prodName" size="20"
						value="${product.prod_name}" class="productTextField" /></td>
				</tr>
				<tr>
					<td>Category</td>
					<td><input type="text" name="prodCategory" size="20"
						value="${product.prod_category}" class="productTextField" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="prodPrice" size="20"
						value="${product.prod_price}" class="productTextField" /></td>
				</tr>
			</table>
			<button class="actionBtn" style="margin-top: 10px">Save</button>
		</form>
	</div>
</body>
		</html>
	</c:otherwise>
</c:choose>