<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">

	<table>
		<tr>
			<th><spring:message code="label.transport.transportcode"/></th>
			<th><spring:message code="label.transport.starttime"/></th>
			<th><spring:message code="label.routes.routecode"/></th>
			<th><spring:message code="label.lines.linename"/></th>
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
