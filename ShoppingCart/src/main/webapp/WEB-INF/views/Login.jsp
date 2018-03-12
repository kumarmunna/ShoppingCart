<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>

<h2>Shopping Cart</h2>
<pre>
	<form:form action="login" method="post" modelAttribute="loginbean">
			<div class="error">
			 <c:out value = "${notexist }"/>
			</div>
		User Name:	<form:input path="username"/> <form:errors path="username" cssClass="error" />
		Password:	<form:password path="password"/> <form:errors path="password" cssClass="error" />
					<input type="submit" value="Login"/>
	</form:form>
</pre>
<form:form action="product">
	<input type="submit">
</form:form>
<form:form action="productList">
	<input type="submit" value="Product List">
</form:form>

<a href="${pageContext.request.contextPath}/productList"> product list </a><br>
<a href="${pageContext.request.contextPath}/submitOrder"> User Registration </a>

</body>
</html>