<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div id="context">
		<%
			int i = 0;
		%>
		<table border="1">
		<thead>
			<tr>
				<td align="center">Number</td>
				<td align="center">Station Code</td>
				<td align="center">Station Name</td>

			</tr>
			</thead>
			<tbody>
			<c:forEach var="station" items="${stationsonlinelist}">
				<tr>
					<td align="center"><%=++i%></td>
					<td align="center">${station.getStationCode()}</td>
					<td align="center">${station.getStationName()}</td>
					
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
