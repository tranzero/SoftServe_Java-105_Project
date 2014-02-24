<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<form action="confirmcreating" method="post">
	<spring:message code="label.lines.linename" /> : <input type="text" name="newLineName"><br>
	<table>
		<tr>
			<td align="center"><spring:message
					code="label.lines.stationcode" /></td>
			<td align="center"><spring:message
					code="label.lines.stationname" /></td>
			<td align="center"><spring:message
					code="label.lines.addstation" /></td>
		</tr>
		<c:forEach var="station" items="${stations}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><input type="checkbox" name="stationsCheck"
					value="${station.getStationId()}"></td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="<spring:message
			code="label.lines.apply" />">
	</form>
</section>