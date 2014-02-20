<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Transports find</h2>
	<form:form action="reservationTicket" method="get">
			From: <input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder="Sknyliv" />
			<p>
			Date: <input type="text" name="dateArriving" placeholder="2013-02-22" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>

		<c:if test="${!empty TransportTravelList}">
			<hr />
			<table>
				<tr>
					<td>Station</td>
					<td>Number</td>
					<td>Departure / Arrival time</td>
					<td>Duration</td>
				</tr>
				<c:forEach var="transport" items="${TransportTravelList}">
				<div>
				</div>
					<tr>
						<td><input type="radio" value="${transport.getLineName()}"></td>
						<td>${transport.getTransport().getTransportCode()}</td>
						<td>dep ${transport.getDepartureTime()}<br />arr
							${transport.getArrivalTime()}
						</td>
						<td>${transport.getDuration()}</td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if
			test="${empty TransportTravelList && not empty param.stationName1 && not empty param.stationName2}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>

</section>
