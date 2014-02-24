<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<spring:message code="label.lines.linename" />
	: <input type="text" value="${lineName}" name="linename"> <br>
	<table>
		<tr>
			<td align="center"><spring:message
					code="label.lines.stationcode" /></td>
			<td align="center"><spring:message
					code="label.lines.stationname" /></td>
			<td align="center"><spring:message
					code="label.lines.removestation" /></td>
		</tr>
		<c:forEach var="station" items="${stationsOnLine}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><a
					href="deletestation/${station.getStationId()}/${lineId}"><spring:message
							code="label.lines.removestation" /></a></td>
			</tr>
		</c:forEach>
	</table>
	<br> <a class="button" href="addstation/${lineId}"><spring:message
			code="label.lines.addstation" /></a> <br> <br> <a
		class="button" href="applychanges"><spring:message
			code="label.lines.apply" /></a>
</section>