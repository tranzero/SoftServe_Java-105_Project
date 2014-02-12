<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<h1>
	<spring:message code="label.orders.allOrders" />
</h1>
<c:if test="${!empty ordersList}">
	<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
	<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>
	<a href=""> <input id="addOrder" type="button" name="addOrder"
		value="<spring:message code="label.orders.addOrder"/>">
	</a>
	<table>
		<thead>
			<tr>
				<th><spring:message code="label.orders.orderId" /></th>
				<th><spring:message code="label.orders.tripId" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="Orders" items="${ordersList}">
				<tr>
					<td>${Orders.getOrderId()}</td>
					<td>${Orders.getTripId()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
