<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Transport find</h2>
	<form:form action="transportTravel" method="get">
			From: <input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder="Sknyliv" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	<%-- Results --%>
	<c:if test="${!empty TransportTravelList}">
		<hr />
		<table>
			<tr>
				<td>Number</td>
				<td>Station / Stop</td>
				<td>Transport Number</td>
				<td>Departure / Arrival time</td>
				<td>Duration</td>
			</tr>
			<c:forEach var="transport" items="${TransportTravelList}">
				<tr>
					<td id="generate"></td>
					<td>${transport.getLineName()}</td>
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
