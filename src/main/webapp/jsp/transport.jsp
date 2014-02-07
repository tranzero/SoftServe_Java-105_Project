<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.transport" />
	</h2>
	<table style="align: center">
		<tr>
			<th><spring:message code="label.transport.transportcode" /></th>
			<th><spring:message code="label.transport.starttime" /></th>
			<th><spring:message code="label.routes.routecode" /></th>
			<th><spring:message code="label.lines.linename" /></th>
			<th><spring:message code="label.transport.seatclass1" /></th>
			<th><spring:message code="label.transport.seatclass2" /></th>
			<th><spring:message code="label.transport.seatclass3" /></th>
			<th><spring:message code="label.transport.genprice" /></th>

		</tr>
		<c:forEach items="${transportsList}" var="transport">
			<tr>
				<td align="center">${transport.getTransportCode()}</td>
				<td align="center">${transport.getStartTime()}</td>
				<td align="center"><a href="">${transport.getRoutes().getRouteCode()}</a></td>
				<td align="center"><a href="">${transport.getRoutes().getLineId().getLineName()}</a></td>
				<td align="center">${transport.getSeatclass1()}</td>
				<td align="center">${transport.getSeatclass2()}</td>
				<td align="center">${transport.getSeatclass3()}</td>
				<td align="center">${transport.getGenPrice()}</td>
				
				<td align="center"><a href="editTransport/${transport.getTransportId()}"><spring:message
							code="label.transport.edit" /></a></td>
							
				<td align="center"><a href="removeTransport/${transport.getTransportId()}"><spring:message
							code="label.transport.delete" /></a></td>
		</c:forEach>
	</table>
</section>
