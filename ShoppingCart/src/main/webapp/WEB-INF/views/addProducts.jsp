<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Products</title>
</head>
<body>
<form:form action="addProduct" method="post" modelAttribute="productbean" enctype="multipart/form-data">
	<pre>
		Code: <form:input path="code" value="abc"/>
		Name:<form:input path="name" value="abc"/>
		Price: <form:input path="price" value="100.0"/>
		Image: <form:input path="data" type="file"/>
		<input type="submit" value="Submit">
</pre>

</form:form>

<form action="editProduct"  method="post" modelAttribute="productbean">
	<input type="hidden" name="productcode" value="abc">
	<input type="submit" value="edit">
</form>

</body>
</html>