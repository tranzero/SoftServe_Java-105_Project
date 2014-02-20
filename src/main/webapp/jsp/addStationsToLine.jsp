<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>ADD STATIONS TO LINE</h1>
	<input type="hidden" value="${lineId}">
	<form action="changestations/${lineId}" method="post">
	
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center">Add</td>
		</tr>
		<c:forEach var="station" items="${allStations}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><input type="checkbox" name="stationsCheck"
					value="${station.getStationId()}">
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="APPLY">
	</form>
	
</section>