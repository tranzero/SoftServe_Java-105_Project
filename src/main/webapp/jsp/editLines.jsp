<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>EDIT LINE</h1>
<%-- 	<input type="hidden" value="${lineId} }"> --%>
	Line Name: <input type="text" value="${lineName}" name="linename"> <br>
	<table>
		<tr>
			<td align="center">Station Code</td>
			<td align="center">Station Name</td>
			<td align="center">Remove</td>
		</tr>
		<c:forEach var="station" items="${stationsOnLine}">
			<tr>
				<td align="center">${station.getStationCode()}</td>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><a
					href="deletestation/${station.getStationId()}/${lineId}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br> <a class="button" href="addstation/${lineId}">Add Station</a>
	<br> <br>
	<a class="button" href="applychanges">Apply</a>
</section>