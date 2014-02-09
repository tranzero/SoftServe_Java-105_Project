<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
<h2><spring:message code="label.navigation.trips"/></h2>
<table style="align: center">
<tr>
<th><spring:message code="label.transport.transportcode"/></th>
<th><spring:message code="label.trips.remseatclass1"/></th>
<th><spring:message code="label.trips.remseatclass2"/></th>
<th><spring:message code="label.trips.remseatclass3"/></th>
<th><spring:message code="label.trips.date"/></th>
<th><spring:message code="label.transport.starttime"/></th>
</tr>
<c:forEach items="${tripsList}" var="trip">
<tr>
<td align="center">${trip.getTransport().getTransportCode()}</td>
<td align="center">${trip.getRemSeatClass1()}</td>
<td align="center">${trip.getRemSeatClass2()}</td>
<td align="center">${trip.getRemSeatClass3()}</td>
<td align="center">${trip.getStartDate()}</td>
<td align="center">${trip.getTransport().getStartTime()}</td>
</tr>
</c:forEach>
</table>
</section>