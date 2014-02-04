<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="context">
	<section>
		<form action="transportTravelFind" method="get">
			From: <input type="text" name="stationName1" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2"/>
			<input class="button" type="submit" name="submit" value="Find" />
		</form>
		<%-- Results --%>
		<c:if test="${!empty TransportList}">
			<hr />
			<table>
				<tr>
					<td>Number</td>
					<td>Station / Stop</td>
					<td>Transport Number</td>
					<td>Departure time</td>
				</tr>
				<c:forEach var="transport" items="${TransportList}">
					<tr>
						<td id="generate"></td>
						<td><c:out value="${param.stationName1}"></c:out> - <c:out
								value="${param.stationName2}"></c:out></td>
						<%--=request.getParameter("stationName1")--%>
						<td>${transport.getTransportCode()}</td>
						<td>${transport.getStartTime()}</td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if test="${empty TransportList}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>
	</section>
</div>
