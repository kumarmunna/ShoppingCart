<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
<form:form action="editAndSaveProduct" method="post" modelAttribute="productbean">
	<pre>
		Code: <form:input path="code" value="${productbean.code }"/><form:errors path="code" cssClass="error" />
		Name:<form:input path="name" value="${productbean.name }"/><form:errors path="name" cssClass="error" />
		Price: <form:input path="price" value="${productbean.price }"/><form:errors path="price" cssClass="error" />
		Image: <form:input path="data" type="file"/>
		<input type="submit" value="Submit">
</pre>

</form:form>

<img src="${pageContext.request.contextPath}/resources/images/${productbean.filename }" width="100" height="100"/>
</body>
</html>