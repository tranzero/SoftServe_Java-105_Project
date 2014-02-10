<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.stationsForUsers" />
	</h2>
	
	<table border="1">
		<thead>
			<tr>
				<th align="center"><spring:message code="label.stations.number" /></th>
				<th><spring:message code="label.stations.stationcode" /></th>
				<th><spring:message code="label.stations.stationname" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="station" items="${stationsList}">
				<tr>
					<td align="center" id="generate"></td>
					<td align="center">${station.getStationCode()}</td>
					<td align="center">${station.getStationName()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
