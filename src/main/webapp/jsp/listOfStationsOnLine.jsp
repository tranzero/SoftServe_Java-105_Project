<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.stationsOnLine.printStationOnLine" />
	</h2>

	<table style="align: center">
		<c:forEach var="stationsOnLine" items="${listOfStationsOnLine}">
			<tr>
				<td align="center" id="generate"></td>
				<td align="center">${stationsOnLine.getStationId().getStationName()}</td>
			</tr>
		</c:forEach>
	</table>
</section>
