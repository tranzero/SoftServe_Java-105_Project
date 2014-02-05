<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<section id="content">
	<h1>Update Lines</h1>
	Name of the line: <input type="text" name="lineName"
		value="${updateLines}"><br>
	<table>
		<tr>
			<td align="center">Stations:</td>
			<td align="center"></td>
		</tr>
		<c:forEach var="station" items="${stationsList}">
			<tr>
				<td align="center">${station.getStationName()}</td>
				<td align="center"><a href="removeStationOnLine/${station.getStationName()}/${updateLines}">delete </a></td>
			</tr>
		</c:forEach>
	</table>
	<a class="button" href="addStationToLine">Add Station To Line</a>
</section>
<!-- </body> -->
<!-- </html> -->