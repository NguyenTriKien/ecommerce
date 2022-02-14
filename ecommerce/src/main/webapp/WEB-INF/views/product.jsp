<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Product</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
	<jsp:include page="_header.jsp" />

	<section class="contact_section">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="form_container">
						<div class="heading_container">
							<h2>Product</h2>
						</div>
						<c:if test="${not empty errorMessage}">
							<div class="error-message">${errorMessage}</div>
						</c:if>
						<form:form modelAttribute="productForm" method="POST" 
						enctype="multipart/form-data" action="${contextPath}/product">
						<table style="text-align: left; font-size: 20px;">
							<tr>
								<td>Code *</td>
								<td><c:if test="${not empty productForm.code}">
										<form:hidden path="code" /> ${productForm.code}
									</c:if> 
									<c:if test="${empty productForm.code}">
										<form:input path="code" />
										<form:hidden path="newProduct" />
								</c:if></td>
				
								<td style="color: red;"><form:errors path="code" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Name *</td>
								<td><form:input path="name" /></td>
								<td style="color: red;"><form:errors path="name" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>CPU *</td>
								<td><form:input path="cpu" /></td>
								<td style="color: red;"><form:errors path="cpu" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>RAM *</td>
								<td><form:input path="ram" /></td>
								<td style="color: red;"><form:errors path="ram" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Screen *</td>
								<td><form:input path="screen" /></td>
								<td style="color: red;"><form:errors path="screen" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>GPU *</td>
								<td><form:input path="gpu" /></td>
								<td style="color: red;"><form:errors path="gpu" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Storage *</td>
								<td><form:input path="storage" /></td>
								<td style="color: red;"><form:errors path="gpu" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>OS *</td>
								<td><form:input path="os" /></td>
								<td style="color: red;"><form:errors path="os" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Quantity *</td>
								<td><form:input path="quantity" /></td>
								<td style="color: red;"><form:errors path="quantity" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Type *</td>
								<td>
								 <form:input path="type.id" id="types1" style="width:250px"/>
								  <form:select path="types" id="typelist" multiple="" onChange="getValue();" style="height:50px">
								     <form:option value="">--Select Type--</form:option>
								     <c:forEach items="${productForm.types}" var="type">
								     	<form:option value="${type.id}">${type.id}</form:option>
								     </c:forEach>
								  </form:select>
                                </td>
								<td style="color: red;"><form:errors path="type" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Producer *</td>
								<td>
								 <form:input path="producer.producerid" id="producers1" style="width:250px"/>
								  <form:select path="producers" id="producerlist" multiple="" onChange="getValue2();" style="height:50px">
								     <form:option value="">--Select Type--</form:option>
								     <c:forEach items="${productForm.producers}" var="producer">
								     	<form:option value="${producer.producerid}">${producer.producerid}</form:option>
								     </c:forEach>
								  </form:select>
                                </td>
								<td style="color: red;"><form:errors path="producer" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Price *</td>
								<td><form:input path="price" /></td>
								<td><form:errors path="price" class="error-message" /></td>
							</tr>
							
							<tr>
								<td>Image</td>
								<td><img
									src="${contextPath}/productImage?code=${productForm.code}"
									width="100" /></td>
								<td></td>
							</tr>
							
							<tr>
								<td>Upload Image</td>
								<td><form:input type="file" path="fileData" /></td>
								<td></td>
							</tr>
							
							<tr>
								<td>&nbsp;</td>
								<td>
									<button style="background-color: green;" type="submit" value="Submit">Submit</button>
									<button type="reset" value="Reset" onclick="customReset();">Reset</button>
								</td>
							</tr>
						</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<div style="margin-bottom: 300px;"></div>
	</section>

	<jsp:include page="_footer.jsp" />
</body>
</html>