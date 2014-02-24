<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<style>
.error {
	color: #ff0000;
}
</style>

<section id="content">
	<h1 align="center"><spring:message code="label.transport.editTransport"/></h1>

	<form:form id="transport" action="addTransport.htm" method="POST" commandName="transport">
	
			<p><form:hidden path="transportId" value="${transport.getTransportId()}" />
				
			<p><spring:message code="label.transport.transportcode"/></p>
			<p><form:input type="text" path="transportCode" onfocus="checkpostal()" />
			<form:errors path="transportCode" cssClass="error" /></p>

			<p><spring:message code="label.transport.starttime" /></p>
			<p><form:input id="startTime" path="startTime" cssClass="startTime" placeholder="HH:MM:SS" />
			<form:errors path="startTime" cssClass="error"/></p>

			<p><spring:message code="label.routes.routecode"/></p>
			<p><form:select path="routes" name="routes">
				<c:forEach var="route" items="${routesList}">
					<option value="${route.getRouteId()}">
						${route.getRouteCode()}&emsp; ${route.getLineId().getLineName()}</option>
				</c:forEach>
			</form:select><form:errors path="routes" cssClass="error"/></p>

			<p><spring:message code="label.transport.seatclass1"/></p>
			<p><form:input path="seatclass1" />
			<form:errors path="seatclass1" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass2"/></p>
			<p><form:input path="seatclass2" />
			<form:errors path="seatclass2" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass3"/></p>
			<p><form:input path="seatclass3" />
			<form:errors path="seatclass3" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.genprice"/></p>
			<p><form:input path="genPrice" />
			<form:errors path="genPrice" cssClass="error"/></p>
	
			<p><input name="control" type="submit" value="Update" />
			
			<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';"></p>
			
	</form:form>

	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.datetimepicker.js"></script>

	<script>
	<!--
		$('#startTime').datetimepicker({
			datepicker : false,
			format : 'H:i:s',
			step : 5
		});
	//-->
	</script>

</section>
