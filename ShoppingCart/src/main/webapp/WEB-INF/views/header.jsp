<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<h2>Online Shopping Cart</h2>
	<a href="${pageContext.request.contextPath}/productList.html">Product
		List</a> |
	<a href="${pageContext.request.contextPath}/getCart.html">My Cart</a> |
	<c:if
		test="${(sessionScope.userstatus eq 'auth') && ((sessionScope.userdetails.userrole eq 'MANAGER') || (sessionScope.userdetails.userrole eq 'EMPLOYEE'))}">
		<a href="${pageContext.request.contextPath}/getOrderList.html">Order
			List</a> |
    </c:if>
	<c:if
		test="${(sessionScope.userstatus eq 'auth') && ((sessionScope.userdetails.userrole eq 'MANAGER'))}">
		<a href="${pageContext.request.contextPath}/product.html">Create
			Product</a> |
    </c:if>
	<c:if test="${sessionScope.userstatus ne 'auth'}">
		<a href="${pageContext.request.contextPath}/loginPage.html">Login</a>  |
				<a href="${pageContext.request.contextPath}/register"> User
			Registration </a>
	</c:if>
	<c:if test="${sessionScope.userstatus eq 'auth'}">
		<a href="${pageContext.request.contextPath}/GetProfile.html"> My
			Profile </a>
	</c:if>
	<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>
	<hr />
</body>
</html>