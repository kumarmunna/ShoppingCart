<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<th>Order ID</th>
			<th>Order Date</th>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Total Amount</th>
			<th>View Details</th>
		</thead>
		<tbody>
			<c:forEach items="${orderlist}" var="list">
				<tr>
					<td>${list.orderid}</td>
					<td>${list.date}</td>
					<td>${list.name}</td>
					<td>${list.email}</td>
					<td>${list.address}</td>
					<td>${list.totalamount}</td>
					<td><a href="${pageContext.request.contextPath}/getOrderDetails.html?orderid=${list.orderid}">View</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>