<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>ADD STATIONS TO LINE</h1>
	<input type="hidden" value="${lineName}">
	<form action="changestations/${lineName}" method="post">
	
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center">Add</td>
		</tr>
		<c:forEach var="station1" items="${existStations}">
			<tr>
				<td align="center">${station1.getStationCode()}</td>
				<td align="center">${station1.getStationName()}</td>
				<td align="center"><input type="checkbox" name="stationsCheck" disabled="disabled"
					checked="checked" value="${station1.getStationName()}">
			</tr>
		</c:forEach>
		<c:forEach var="station2" items="${allStations}">
			<tr>
				<td align="center">${station2.getStationCode()}</td>
				<td align="center">${station2.getStationName()}</td>
				<td align="center"><input type="checkbox" name="stationsCheck"
					value="${station2.getStationName()}">
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="APPLY">
	</form>
	
</section>