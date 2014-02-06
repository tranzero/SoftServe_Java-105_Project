<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>All Stations</h1>
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center">Add</td>
		</tr>
		<c:forEach var="station" items="${stationsList}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><input type = "checkbox"></td>
			</tr>
		</c:forEach>
	</table>
	<a class="button" href="confirmaddstations">OK</a>
</section>