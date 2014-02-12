<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h1>
		<spring:message code="label.orders.allOrders" />
	</h1>
	<table class='table'>
		<thead>
			<tr>
				<th><spring:message code="label.orders.orderId" /></th>
				<th><spring:message code="label.orders.tripId" /></th>
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
	<a href=""> <input id="addOrder" type="button" name="addOrder"
		value="<spring:message code="label.orders.addOrder"/>">
	</a>
	<hr />
</section>