<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Your orders</h1>

<table class='table'>
	<thead>
		<tr>
			<th>OrderId</th>
			<th>TripId</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="orders" items="${ordersList}">
			<tr>
				<td align="center">${orders.getOrderId()}</td>
				<td align="center">${orders.getTripId()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<hr />