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
	<form method="GET" action="${contextPath}/producerList">
		<div class="container">
			<div class="heading_container heading_center">
				<h2>Producer List</h2>				
					<table>
						<tr>
							<td><label style="margin-right: 5px;">Name</label>
							<input style="margin-right: 5px; width: 70%" type="text" name="producername"></td>
							<td><label style="margin-right: 5px;">Country</label>
							<input style="margin-right: 17px; width: 65%" type="text" name="country"></td>
							<td colspan="2" align="center">
								<input style="color: white; background-color: #33CC00; border-radius: 5px; margin-top: -10px" type="submit" value="Search">
							</td>
						</tr>
					</table>		
			</div>
			<table border="2" style="width: 100%">
				<tr>
					<th>Producer ID</th>
					<th>Name</th>
					<th>Country</th>
					<th>CRUD</th>
				</tr>
				<c:forEach items="${paginationProducerInfos.list }" var="producerInfo">
					<tr>
						<td style="text-align: center;">${producerInfo.producerid}</td>
						<td style="text-aline:center;">${producerInfo.producername}</td>
						<td style="text-aline:center;">${producerInfo.country}</td>
						<td colspan="2"><a href="${contextPath}/producer?producerid=${producerInfo.producerid}">Edit</a> 
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
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