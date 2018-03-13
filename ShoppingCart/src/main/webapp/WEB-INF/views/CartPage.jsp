<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Cart</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css"/>

</head>
<body>

	<c:if test="${empty cartInfo}">
		<h1>Your Cart is empty. Please add product in cart.</h1>
	</c:if>
	<c:set var="count" value="0" scope="page" />
	<c:forEach items="${cartInfo}" var="item">
		<c:set var="count" value="${count + 1}" scope="page" />
		<div style="float: left; border: 1px solid red;">
			<img
				src="${pageContext.request.contextPath}/resources/images/${item.filename}" /><br>
			<div style="padding-left: 2em; padding-bottom: 1em;">
				Code: ${item.code}<br> Name: ${item.name}<br> Price:
				${item.price}<br> Quantity:<input type="text" size="1"
					id="qty${count}" value="${item.quantity}"
					onchange='fun(this.id,"${item.code}")'> <br>
				<script type="text/javascript">
					function fun(id, code) {
						var qty = document.getElementById(id).value;
						window.top.location.href = "${pageContext.request.contextPath}/updateQuantity?code="
								+ code + "&qty=" + qty;
					}
				</script>
				<%-- <a href="" onclick='fun("${item.code}")'>qty</a><br> --%> Subtotal:
				${item.subtotal}<br> 
					 <a
					href="${pageContext.request.contextPath}/removeFromCart?productCode=${item.code}">Remove
					from cart </a><br>
			</div>
		</div>
	</c:forEach>
	<br>
	<div style="float: right;">
		<a href="${pageContext.request.contextPath}/productList">Continue
			Buying</a> <br>
			<c:if test="${not empty cartInfo}">
		<c:choose>
			<c:when test="${sessionScope.userstatus ne 'auth'}">
				<a id="myBtn" href="#">Submit Order</a>
			</c:when>
			<c:otherwise>
			
				<a href="${pageContext.request.contextPath}/submitOrder">Submit
					Order</a>			
			
			</c:otherwise>
		</c:choose>
		</c:if>
	</div>
	<br>

	<c:if test="${sessionScope.userstatus ne 'auth'}">
		<!-- The Modal -->
		<div id="myModal" class="modal">
			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<p>To save and submit order</p>
				<p>
					Please <a href="${pageContext.request.contextPath}/login">
						Login </a>
				</p>
				<p>
					If new user? Please <a
						href="${pageContext.request.contextPath}/register"> Register </a>
					yourself
				</p>
			</div>
		</div>
	</c:if>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>