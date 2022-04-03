<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Type List</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
</head>
<body>
	<jsp:include page="_header.jsp" />
	
	<section class="feature_section layout_padding">
		<div class="container">
			<div class="heading_container">
				<h2>Type List</h2>
			</div>
			<table border="2" style="width: 100%">
				<tr>
					<th>Type ID</th>
					<th>Name</th>
					<th>CRUD</th>
				</tr>
				<c:forEach items="${paginationTypeInfos.list }" var="typeInfo">
					<tr>
						<td style="text-align: center;">${typeInfo.id}</td>
						<td style="text-aline:center;">${typeInfo.typename}</td>
						<td colspan="2"><a href="${contextPath}/type?id=${typeInfo.id}">Edit</a> 
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div style="margin-bottom: 10px;"></div>
	</section>
	
	<c:if test="${paginationOrderInfos.totalPages > 1 }">
		<div style="text-align:center;">
			
			<c:forEach items="${paginationOrderInfos.navigationPages }" var="page">
				<c:if test="${page != -1 }">
					<a style="font-size: 20px;" href="orderList?page=${page }" class="nav-item">${page }</a>
				</c:if>
				<c:if test="${page == -1 }">
					<span>...</span>
				</c:if>
			</c:forEach>
		</div>
		<div style="margin-bottom: 50px;"></div>
	</c:if>
	
	<jsp:include page="_footer.jsp" />
</body>
</html>