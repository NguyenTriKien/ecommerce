<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
    <jsp:include page="_header.jsp" />
	<fmt:setLocale value="en_US" scope="session" />
	
	<section class="shop_section layout_padding">
	<form method="GET" action="${contextPath}/manageProductList">
		<div class="container" >
			<div class="heading_container heading_center">
				<h2>Product List</h2>				
					<table>
						<tr>
							<td><label style="margin-right: 5px;">Name</label>
							<input style="margin-right: 5px; width: 75%" type="text" name="name"></td>
							<td><label style="margin-right: 5px;">Price</label><input style="margin-right: 5px; width: 80%" type="text" name="price"></td>
							<td colspan="2" align="center">
								<input style="color: white; background-color: #33CC00; border-radius: 5px; margin-top: -10px" type="submit" value="Search">
							</td>
						</tr>
					</table>		
			</div>
	</section>
		<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Code</th>
							<th>Name</th>
							<th>Type</th>
							<th>Quantity</th>
							<th>Price</th>
	                        <th>CRUD</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="productInfo"
							items="${paginationProductInfos.list}">
							<tr>
								<td><c:out value="${productInfo.code}"></c:out></td>
								<td><c:out value="${productInfo.name}"></c:out></td>
								<td><c:out value="${productInfo.type.id}"></c:out></td>
								<td><c:out value="${productInfo.quantity}"></c:out></td>
								<td><c:out value="${productInfo.price}"></c:out></td>
								<td colspan="2">
									<li>
										<a style="color: red;" href="${contextPath}/product?code=${productInfo.code}">Edit Product</a>
										
									</li>
									<li>
										<a style="color: red;" href="${contextPath}/removeProduct?code=${productInfo.code}">Delete Product</a>
									</li>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	<br />
	<c:if test="${paginationProductInfos.totalPages > 1}">
		<div style="text-align:center;">
			<c:forEach items="${paginationProductInfos.navigationPages}" var="page">
				<c:if test="${page != -1}">
					<a style="font-size: 20px;" href="productList?page=${page}">${page}</a>
				</c:if>
				<c:if test="${page == -1}">
					<span class="nav-item"> ... </span>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
	
	<jsp:include page="_footer.jsp" />	
</body>
</html>