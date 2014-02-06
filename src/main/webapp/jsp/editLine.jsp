<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>Edit/Create Line</h1>
	Line name: <input type="text" value="${lineName}">
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
	<a class="button" href="addnewstations">Add Stations</a>
</section>