<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.error {
	color: #ff0000;
}
</style>

<section id="content">
	<h1 align="center"><spring:message code="label.transport.editTransport"/></h1>

	<form:form id="transport" action="addTransport.htm" method="POST" commandName="transport">
				
			<p><form:input type="hidden" path="transportId" 
				value="${transport.getTransportId()}" onfocus="checkpostal()" />
			
			<p><spring:message code="label.transport.transportcode"/></p>
			<p><form:input type="text" path="transportCode" 
				onfocus="checkpostal()" value="${transport.getTransportCode()}"/>
			<form:errors path="transportCode" cssClass="error" /></p>

			<p><spring:message code="label.transport.starttime" /></p>
			<p><form:input type="text" path="startTime" 
				cssClass="startTime" placeholder="HH:MM:SS" value="${transport.getStartTime()}"/>
			<form:errors path="startTime" cssClass="error"/></p>
	
			<p><spring:message code="label.routes.routecode"/></p>
			<p><form:input path="routes"
				value="${transport.getRoutes().getRouteId()}" />
			<form:errors path="routes" cssClass="error"/></p>
				
			<p><spring:message code="label.transport.seatclass1"/></p>
			<p><form:input path="seatclass1" value="${transport.getSeatclass1()}" />
			<form:errors path="seatclass1" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass2"/></p>
			<p><form:input path="seatclass2" value="${transport.getSeatclass2()}" />
			<form:errors path="seatclass2" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass3"/></p>
			<p><form:input path="seatclass3" value="${transport.getSeatclass3()}" />
			<form:errors path="seatclass3" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.genprice"/></p>
			<p><form:input path="genPrice" value="${transport.getGenPrice()}"/>
			<form:errors path="genPrice" cssClass="error"/></p>
	
			<p><input name="control" type="submit" value="Update" />
			
			<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';"></p>
			
	</form:form>
	
</section>
