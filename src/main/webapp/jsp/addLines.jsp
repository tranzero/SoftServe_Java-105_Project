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
<div id="context">
	<h1>Add Lines</h1>
	<form target="newLine" method="post">
		Name of line: <br> <input id="name" type="text" name="name">
		<br> Choose the stations on line:<br>
		<c:forEach var="stations" items="${stationsList}">
		<input type="checkbox" value="${stations.getStationName()}">${stations.getStationName()}<br>
		</c:forEach>
		<input type="submit" value="button" form="newLine">
	</form>
</div>
<!-- </body> -->
<!-- </html> -->