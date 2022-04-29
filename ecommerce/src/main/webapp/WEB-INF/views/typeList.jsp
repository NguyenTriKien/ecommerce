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
<style>
#addType {
    padding: 19px 39px 18px 39px;
	color: white;
	background-color: salmon;
	font-size: 15px;
	font-weight: bold;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 200px;
	border: 1px solid black;
	border-width: 1px 1px 1px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin-bottom: 10px;
}
</style>
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
		  <div style="color: red;">
		   <a id="addType" class="btn btn-info" style="margin-bottom: 5px; margin-top: 5px; border-radius: 5px;" href="${contextPath}/type"> 
              Add type
           </a>   
         </div>
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