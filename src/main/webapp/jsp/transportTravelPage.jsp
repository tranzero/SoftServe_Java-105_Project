<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Results --%>
<c:if test="${!empty TransportTravelList}">
	<hr />
	<table>
		<tr>
			<th>N</th>
			<th><a href="javascript:void(0);"
				onclick="showTransportPage('${param.stationName1}','${param.stationName2}', '${param.sDate}', ${pageNumber},${resultsPerPage}, '1')">
					<spring:message code="label.transportTravel.stationStop" />
			</a></th>
			<th><a href="javascript:void(0);"
				onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '2')">
					<spring:message code="label.transportTravel.number" />
			</a></th>
			<th><a href="javascript:void(0);"
				onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '3')">
					<spring:message code="label.transportTravel.depArr" />
			</a></th>
			<th><a href="javascript:void(0);"
				onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '4')">
					<spring:message code="label.transportTravel.duration" />
			</a></th>
			<c:if test="${not empty param.sDate}">
				<c:if test="${not empty user}">
					<th colspan="3"><spring:message code='label.tickets.purchase' /></th>
				</c:if>
			</c:if>
		</tr>
		<c:forEach var="transport" items="${TransportTravelList}">
			<tr>
				<td id="generate"></td>
				<td>${transport.getLineName()}</td>
				<td>${transport.getTransport().getTransportCode()}</td>
				<td><spring:message code="label.transportTravel.dep" />
					${transport.getDepartureTime()}<br /> <spring:message
						code="label.transportTravel.arr" /> ${transport.getArrivalTime()}</td>
				<td>${transport.getDuration()}</td>
				<c:if test="${not empty param.sDate}">
					<c:if test="${not empty user}">
						<td><a
							href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/1">Class
								1</a><br />(${transport.getTrip().getRemSeatClass1()})</td>
						<td><a
							href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/2">Class
								2</a><br />(${transport.getTrip().getRemSeatClass2()})</td>

						<td><a
							href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/3">Class
								3</a><br />(${transport.getTrip().getRemSeatClass3()})</td>
					</c:if>
				</c:if>
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

<c:if
	test="${not empty param.stationName1 && not empty param.stationName2}">

	<div class="pagination">
		<ul class="bootpag">
			<c:if test="${pageNumber>1}">
				<li class="prev"><a href="javascript:void(0);"
					onclick="showTransportPage('${param.stationName1}','${param.stationName2}',1, ${resultsPerPage}, '${param.sDate}', '${param.orderBy}')">
						« </a></li>
				<li class="prev"><a href="javascript:void(0);"
					onclick="showTransportPage('${param.stationName1}','${param.stationName2}',${pageNumber-1},${resultsPerPage}, '${param.sDate}', '${param.orderBy}')">
						<spring:message code="label.prev" />
				</a></li>
			</c:if>
			<c:if test="${pageNumber==1}">
				<li class="prev disabled"><a href="javascript:void(0);"> «
				</a></li>
				<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
							code="label.prev" />
				</a></li>
			</c:if>
			<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
				varStatus="status">
				<c:if test="${pageNumber!=i}">
					<li><a href="javascript:void(0);"
						onclick="showTransportPage('${param.stationName1}','${param.stationName2}',${i},${resultsPerPage}, '${param.sDate}', '${param.orderBy}')">
							${i} </a></li>
				</c:if>
				<c:if test="${pageNumber==i}">
					<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
				</c:if>
			</c:forEach>

			<c:if test="${pageNumber<maxPages}">
				<li class="next"><a href="javascript:void(0);"
					onclick="showTransportPage('${param.stationName1}','${param.stationName2}',${pageNumber+1},${resultsPerPage}, '${param.sDate}', '${param.orderBy}')">
						<spring:message code="label.next" />
				</a></li>
				<li class="next"><a href="javascript:void(0);"
					onclick="showTransportPage('${param.stationName1}','${param.stationName2}',${maxPages},${resultsPerPage}, '${param.sDate}', '${param.orderBy}')">
						» </a></li>
			</c:if>
			<c:if test="${pageNumber==maxPages}">
				<li class="next disabled"><a href="javascript:void(0);"> <spring:message
							code="label.next" />
				</a></li>
				<li class="next disabled"><a href="javascript:void(0);"> »
				</a></li>
			</c:if>
		</ul>
	</div>
</c:if>