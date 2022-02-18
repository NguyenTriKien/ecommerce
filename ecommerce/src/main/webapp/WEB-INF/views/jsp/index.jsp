<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Paypal Payment with Spring Boot - ShareEverythings.com</title>
</head>
<body>
	<h1>Paypal Payment with PayPal</h1>
	<form action="pay" method="post">
		<h1 style="color:red; margin: 0px auto;">Tổng tiền bạn cần thanh toan là:  ${sessionScope.orderDTO.totalPrice}</h1>
		    <span> <input type="hidden" value="${orderDTO.usd}" name="price" /></span>
		    <c:out value="${sessionScope.orderDTO.usd}"></c:out>
		<button class="btn btn--primary"  style="margin:10px; background-color:orange" type="submit">Payment with Paypal</button>
	</form>
</body>
</html>