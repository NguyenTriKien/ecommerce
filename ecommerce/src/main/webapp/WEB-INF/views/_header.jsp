<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="name" value="${pageContext.request.userPrincipal.name}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
</head>
<style>
/* Dropdown Button */
.dropbtn {
  background-color: white;
  color: black;
  padding: 20px;
  font-size: 16px;
  margin-top: -15px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: white}
</style>
<body>
	<header class="header_section">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-lg custom_nav-container">
				<a class="navbar-brand" href="${contextPath}/">
					<span>Online Shop</span>
				</a>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav">
					<li class="nav-item active">
						<a class="nav-link" href="${contextPath}/manageProductList">Home</a>
					</li>
				    <div class="dropdown">
					  <button class="dropbtn" onClick="location.href='${contextPath}/manageProductList'">PRODUCT LIST</button>
					  <div class="dropdown-content">
					    <a href="${contextPath}/manageProductList/producttype?type=Phone">Phone</a>
					    <a href="${contextPath}/manageProductList/producttype?type=Laptop">Laptop</a>
					  </div>
					</div>
					<security:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/product">Create Product</a>
					</li>
					</security:authorize>
					
					<security:authorize access="hasRole('ROLE_MANAGER')">
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/orderList">Order List</a>
					</li>
					<li class="nav-item">
						<a class="nav-link"  href="${contextPath}/accountList">User Management </a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/typeList">Type</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/producerList">Producer</a>
					</li>
					</security:authorize>
				</ul>
				<div class="user_option-box" style="padding-bottom: 10px;">
					<c:if test="${name != null}">
						Hello <a href="${contextPath }/accountInfo">${name}</a>
					&nbsp; &nbsp;
					<a href="${contextPath }/logout">Logout</a>
					</c:if>
					<c:if test="${name == null}">
						<a href="${contextPath }/login">Login</a>
					</c:if>
				</div>
			</div>
			</nav>
		</div>
	</header>
	
	<!-- 
	<div class="header-container"> 
		<div class="container-fluid">Online Shop</div>
		<div class="header-bar">
			<c:if test="${name != null}">
				Hello <a href="${contextPath }/accountInfo">${name }</a>
				&nbsp; &nbsp;
				<a href="${contextPath }/logout">Logout</a>
			</c:if>
			<c:if test="${name == null}">
				<a href="${contextPath }/login">Login</a>
			</c:if>
		</div>
	</div>
	 -->
	
</body>
</html>