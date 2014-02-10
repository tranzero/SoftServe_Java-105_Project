<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>Edit/Create Line</h1>
	<form action="savenewline" method="post">
		Line name: <input type="text" value="${lineName}" name="newLineName"><br>
		<input type="submit" value="Save line">
	</form>
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center"></td>
		</tr>
		<c:forEach var="station" items="${stationsList}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><a
					href="removestation/${station.getStationId()}/${lineId}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a class="button" href="/SoftServe_Java-105/addnewstations/${lineId}">Add
		Stations</a>
</section>