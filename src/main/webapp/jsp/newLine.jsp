<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>NEW LINE</h1>
	<form action="confirmcreating" method="post">
	Line Name: <input type="text" name="newLineName"><br>
	Stations:
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center">Add</td>
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
	<input type="submit" value="Confirm">
	</form>
</section>