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

<div style="text-align: center;">


	<form:form action="login" method="post" modelAttribute="loginbean">
			<div class="error">
			 <c:out value = "${notexist }"/>
			</div><br>
User Name:	<form:input path="username"/> <form:errors path="username" cssClass="error" /><br><br>
	Password:	<form:password path="password"/> <form:errors path="password" cssClass="error" /><br><br>
					<input type="submit" value="Login"/>
	</form:form>
	
	</div>
</body>
</html>