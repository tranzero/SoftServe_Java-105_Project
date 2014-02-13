<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
	<form action="addTransport.htm" method="post" name="transport">
		<div>
				<p><spring:message code="label.transport.transportcode"/></p>
				<p><input type="text" name="transportCode"></p>

				<p><spring:message code="label.transport.starttime"/></p>
				<p><input type="text" name="startTime"></p>

				<p><spring:message code="label.routes.routecode"/></p>
				<p><input type="text" name="routes"></p>

				<p><spring:message code="label.transport.seatclass1"/></p>
				<p><input type="text" name="seatclass1"></p>
			
				<p><spring:message code="label.transport.seatclass2"/></p>
				<p><input type="text" name="seatclass2"></p>

				<p><spring:message code="label.transport.seatclass3"/></p>
				<p><input type="text" name="seatclass3"></p>

				<p><spring:message code="label.transport.genprice"/></p>
				<p><input type="text" name="genprice"></p>

		</div>
		<input type="submit" value="<spring:message code="label.navigation.addTransport"/>">&emsp;
		<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';">
	</form>
</section>
