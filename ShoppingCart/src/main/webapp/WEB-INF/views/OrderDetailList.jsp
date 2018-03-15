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
			<th>Product Code</th>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Prouct Price</th>
			<th>Total Amount</th>
		</thead>
		<tbody>
			<c:forEach items="${orderdetaillist}" var="list">
				<tr>
					<td>${list.orderid}</td>
					<td>${list.productCode}</td>
					<td>${list.productName}</td>
					<td>${list.quantity}</td>
					<td>${list.productPrice}</td>
					<td>${list.totalAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>