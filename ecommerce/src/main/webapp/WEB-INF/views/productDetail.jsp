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
<title>Product Detail</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<section class="feature_section layout_padding">
			<div class="container">
				<div class="heading_container">
					<h2>Product Detail</h2>
				</div>
			</div>
		</section>
	<div style="margin-left: 450px; margin-bottom: 5px; margin-right: 450px; margin-top:-50px">
		<div style="color: #000080; margin-left: 200px; ">
			<h3>Product Information</h3>				
		</div>
		<ul style="font-size: 20px; margin-left: 200px; list-style-type: none;">
			<li>
				<img style="width: 200px; height: 200px;" src="${contextPath}/productImage?code=${productInfo.code}" />
			</li>
		</ul>
	</div>
	<br/>
	<table border="2" style="width: 100%; font-size: 20px;">
	      <thead>
		      <tr style="text-align: center;">
					<th>Product Name</th>
					<th>CPU</th>
					<th>GPU</th>
					<th>Screen</th>
					<th>RAM</th>
					<th>Storage</th>
					<th>OS</th>
					<th>Producer</th>
					<th>Country</th>
					<th>Price</th>
				</tr>
	      </thead>
		  <tbody>
		     <td>${productInfo.name }</td>
		     <td>${productInfo.cpu }</td>
		     <td>${productInfo.gpu }</td>
		     <td>${productInfo.screen }</td>
		     <td>${productInfo.ram }</td>
		     <td>${productInfo.storage }</td>
		     <td>${productInfo.os }</td>
		     <td>${productInfo.producer.producerid }</td>
		     <td>${productInfo.producer.country }</td>
		     <td>${productInfo.price }</td>
		  </tbody>
		</table>
</body>
</html>