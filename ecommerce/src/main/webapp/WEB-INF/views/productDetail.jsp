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
       <section class="product-details spad">
        <div class="container" style="margin-top:50px">
            <div class="row" style="width:100%">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="${contextPath}/productImage?code=${productInfo.code}" alt="" style="width:100%">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3>Product Detail</h3>
                        <div class="product__details__rating">
                           <table style="width:95%; ">
						   <tr>
						       <th style="font-size: 20px;">Name</th>
						       <th style="font-weight:normal; font-size: 20px;">${productInfo.name}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">CPU</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.cpu}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">RAM</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.ram}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Screen(inches)</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.screen}</th>
						   </tr>
						    <tr>
							   <th style="font-size: 20px;">GPU</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.gpu}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Storage</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.storage}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Operating system</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.os}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Type</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.type.typename}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Producer</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.producer.producerid}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Country</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.producer.country}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Status</th>
							   <th style="font-weight:normal; font-size: 20px;">${productInfo.status}</th>
						   </tr>
						   <tr>
							   <th style="font-size: 20px;">Price:</th>
							   <th style="font-weight:normal; font-size: 20px">
							      <fmt:formatNumber value="${productInfo.price}" type="currency"></fmt:formatNumber>
							   </th>
							</tr>
                           </table>
                        </div>
                        <br>
                        <a style="font-size: 20px;" href="${contextPath}/buyProduct?code=${productInfo.code}" class="primary-btn">BUY NOW</a>
                        <a href="#" clastyle="font-size: 20px;"ss="heart-icon"><span class="icon_heart_alt"></span></a>
                    </div>
                </div>
               </div>
              </div>
    </section>
  </body>
</html>
