<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="error">
			 <c:out value = "${success }"/>
			</div>
<form:form action="UpdateAddress.html" modelAttribute="userdetails">

	User Name: <form:label path="username" >${userdetails.username }</form:label> <form:errors path="username" cssClass="error" /><br>
	<form:hidden path = "username" value = "${userdetails.username }" />
	Name: <form:label path="name"></form:label> ${userdetails.name }<form:errors path="name" cssClass="error" /><br>
	<form:hidden path = "name" value = "${userdetails.name }" />
	Address: <form:input  path="address" /> <form:errors path="address" cssClass="error" /><br>
	Phone: <form:label path="phone">${userdetails.phone }</form:label> <form:errors path="phone" cssClass="error" /><br>
	<form:hidden path = "phone" value = "${userdetails.phone }" />
	Email: <form:label path="email">${userdetails.email }</form:label> <form:errors path="email" cssClass="error" /><br>
	<form:hidden path = "email" value = "${userdetails.email }" />
	User Role: <form:label path="userrole">${userdetails.userrole }</form:label> <form:errors path="userrole" cssClass="error" />
	<form:hidden path = "userrole" value = "${userdetails.userrole }" /><br><br>
	<input type="submit" value="Update Information">
</form:form>
</body>
</html>