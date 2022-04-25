<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
<title>Quantity notification</title>
</head>
<body>
<jsp:include page="userHeader.jsp" />
	<fmt:setLocale value="en_US" scope="session" />
	
	<section class="shop_section layout_padding">
		<div class="container" >
			<div class="heading_container heading_center">
				<h2>My Cart</h2>
			</div>
			
				<h3>Sorry, this product is not available right now</h3>
				<a href="${contextPath}/productList">Return to product list</a>
				</div>
	</section>
    <div style="margin-bottom: 480px;"></div>
	
	<jsp:include page="_footer.jsp" />
</body>
</html>