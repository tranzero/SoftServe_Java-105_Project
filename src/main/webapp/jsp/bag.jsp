<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>Shopping bag</title>
<section id="content">
	<form:form id="bag" action="/SoftServe_Java-105/bag" method="post">

		<c:if test="${!empty ticketsList}">
			<table>
				<thead>
					<tr>
						<th align="center">Customer first name</th>
						<th align="center">Customer last name</th>
						<th align="center">Route</th>
						<th align="center">Date</th>
						<th align="center">Price</th>
					</tr>
				</thead>

				<c:forEach var="ticket" items="${ticketsList}">
					<tbody>
						<tr>
					     	<td align="center">${ticket.getCustomerFirstName()}</td>
							<td align="center">${ticket.getCustomerLastName()}</td>
							<td align="center">${ticket.getTrip().getTransport().getRoutes().getRouteName()}</td>
							<td align="center">${ticket.getTrip().getStartDate()}</td>
							<td align="center">${ticket.getTrip().getTransport().getGenPrice()}</td>
							<td align="center"><a
								href="delete/${ticket.getTicketName()}/${ticket.getTrip().getTripId()}"><input
									id="delete" type="button" name="delete" value="delete" /> </a></td>
						</tr>

					</tbody>
				</c:forEach>
			</table>
			<a href="/SoftServe_Java-105/bagPay"> <input id="cartData" type="button"
				name="cartData" value="Buy Tickets">
			</a>
		</c:if>
	</form:form>

	<c:if test="${empty ticketsList }">
		<p>Your shopping bag is empty.</p>
	</c:if>
</section>