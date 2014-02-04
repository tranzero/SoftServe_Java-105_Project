<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">

	<table border="1">
		<tr>
			<td>Transport Code</td>
			<td>Start Time</td>
			<td>Route Code</td>
			<td>Line Name</td>
		</tr>
		<c:forEach items="${transportsList}" var="transport">
			<tr>
				<td>${transport.getTransportCode()}</td>
				<td>${transport.getStartTime()}</td>
				<td>${transport.getRoutes().getRouteCode()}</td>
				<td>${transport.getRoutes().getLineId().getLineName()}</td>
		</c:forEach>
	</table>
	</div>
<!-- </body> -->
<!-- </html> -->
