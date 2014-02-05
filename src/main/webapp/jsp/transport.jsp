<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<table>
		<tr>
			<th><spring:message code="label.transport.transportcode"/></th>
			<th><spring:message code="label.transport.starttime"/></th>
			<th><spring:message code="label.routes.routecode"/></th>
			<th><spring:message code="label.lines.linename"/></th>
			<th><spring:message code="label.transport.seatclass1"/></th>
			<th><spring:message code="label.transport.seatclass2"/></th>
			<th><spring:message code="label.transport.seatclass3"/></th>
			<th><spring:message code="label.transport.genprice"/></th>
		</tr>
		<c:forEach items="${transportsList}" var="transport">
			<tr>
				<td align="center">${transport.getTransportCode()}</td>
				<td align="center">${transport.getStartTime()}</td>
				<td align="center"><a href="">${transport.getRoutes().getRouteCode()}</a></td>
				<td align="center"><a href="stations">${transport.getRoutes().getLineId().getLineName()}</a></td>
				<td align="center">${transport.getSeatclass1()}</td>
				<td align="center">${transport.getSeatclass2()}</td>
				<td align="center">${transport.getSeatclass3()}</td>
				<td align="center">${transport.getGenPrice()}</td>
		</c:forEach>
	</table>
</section>
