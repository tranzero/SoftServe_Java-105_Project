<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Title</title>
</head>
<body>
<div id="">
	<h1>
		get Lines by two Stations
	</h1>
	<form action="linesFind" method="get">
		From: <input type="text" name="stationName1" />&nbsp;&nbsp;&nbsp;
		To: <input type="text" name="stationName2" />
		<input type="submit" name="submit" value ="Find" />
	</form>
</div>
<c:if test="${!empty LinesList}">
<hr />
	<ol>
	<c:forEach var="lines" items="${LinesList}">
		<li>${lines.getLineName()}</li>
	</c:forEach>
	</ol>
<hr />
</c:if>
<c:if test="${empty LinesList}">
<hr />
	<h3>Sorry. No results was found</h3>
<hr />
</c:if>
</body>
</html>