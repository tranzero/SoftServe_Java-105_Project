<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<%
		int i = 0;
	%>
	<table border="1">
		<tr>
			<td>Number</td>
			<td>Station Code</td>
			<td>Station Name</td>

		</tr>
		<c:forEach var="station" items="${stationsList}">
			<tr>
				<td><%=++i%></td>
				<td>${station.getStationCode()}</td>
				<td>${station.getStationName()}</td>

			</tr>
		</c:forEach>
	</table>
	</div>
<!-- </body> -->
<!-- </html> -->