<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Coffee Shop</title>
</head>
<body>
<h2>All Orders</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Coffee Type</th>
        <th>Dessert Type</th>
        <th>Order Time</th>
        <th>Work Schedule</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.coffeeType}</td>
            <td>${order.dessertType}</td>
            <td>${order.orderTime}</td>
            <td>${order.workSchedule}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>