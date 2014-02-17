<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h1 align="center">Edit Transport</h1>
	<form id="editTransport" method="post" name="transport">
		<div>

			<p><input id="transportId" type="hidden" name="transportId"
				value="${transport.getTransportId()}"></p>

			<p><spring:message code="label.transport.transportcode" /></p>
			<p><input id="transportCode" type="text" name="transportCode"
				value="${transport.getTransportCode()}"></p>

			<p><spring:message code="label.transport.starttime" /></p>
			<p><input id="startTime" type="text" type="text" name="startTime" 
				value="${transport.getStartTime()}"></p>

			<p><spring:message code="label.routes.routecode" /></p>
			<p><input id="routes" type="text" name="routes"
				value="${transport.getRoutes().getRouteCode()}" /></p>

			<p><spring:message code="label.transport.seatclass1" /></p>
			<p><input id="seatclass1" type="text" name="seatclass1"
				value="${transport.getSeatclass1()}" /></p>

			<p><spring:message code="label.transport.seatclass2" /></p>
			<p><input id="seatclass2" type="text" name="seatclass2"
				value="${transport.getSeatclass2()}" /></p>

			<p><spring:message code="label.transport.seatclass3" /></p>
			<p><input id="seatclass3" type="text" name="seatclass3"
				value="${transport.getSeatclass3()}" /></p>

			<p><spring:message code="label.transport.genprice" /></p>
			<p><input id="genprice" type="text" name="genprice"
				value="${transport.getGenPrice()}" /></p>

			<p><input type="submit" value="Update">&emsp;
				<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';"></p>
		</div>
	</form>
</section>
