<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.tablesorter.min.js"></script>
<script src="resources/js/jquery.searcher.js"></script>

<section id="content">
<h1><spring:message code="label.Orders.myOrders"/></h1>
	<p>
		<label for="inputSearchTicket"><spring:message code="label.Orders.searchTicket"/></label>
	    <input id="inputSearchTicket" type="text" />
	</p>
	<table id="tableTickets">
		<thead>
			<tr>
				<th><spring:message code="label.Orders.ticketName"/></th>
				<th><spring:message code="label.Orders.orderId"/></th>
				<th><spring:message code="label.Orders.date"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tickets" items="${ticketsList}">
				<tr>
					<td>${tickets.getTicketName()}</td>
					<td>${tickets.getOrder().getOrderId()}</td>
					<td>${tickets.getOrder().getOrderDate()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<script type="text/javascript">
	$("#tableTickets").searcher({
		inputSelector : "#inputSearchTicket"
	});
</script>
<script type="text/javascript">
	$(function() {
		$("#tableTickets").tablesorter();
	});
</script>
