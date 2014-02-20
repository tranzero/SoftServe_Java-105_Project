<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/addTransportValidation.js"></script>
		
<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
	<form id="addTransport.htm" action="addTransport.htm" method="post" name="transport">
		<div>
			<p><spring:message code="label.transport.transportcode"/></p>
			<p><input id="transportCode" type="text" name="transportCode"></p>

			<p><spring:message code="label.transport.starttime"/></p>
			<p><input id="startTime" type="text" name="startTime" ></p>

			<p><label id="routes"><spring:message code="label.routes.routecode"/></label></p>
			<select name="routes">
				<c:forEach var="route" items="${routesList}">
					<option value="${route.getRouteId()}">
						${route.getRouteCode()}&emsp; ${route.getLineId().getLineName()}</option>
				</c:forEach>
			</select>
				
			<p><spring:message code="label.transport.seatclass1"/></p>
			<p><input id="seatclass1" type="text" name="seatclass1"></p>
			
			<p><spring:message code="label.transport.seatclass2"/></p>
			<p><input id="seatclass2" type="text" name="seatclass2"></p>

			<p><spring:message code="label.transport.seatclass3"/></p>
			<p><input id="seatclass3" type="text" name="seatclass3"></p>

			<p><spring:message code="label.transport.genprice"/></p>
			<p><input id="genprice" type="text" name="genprice"></p>
				
		</div>
		<input type="submit" value="<spring:message code="label.navigation.addTransport"/>">&emsp;
		<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';">
	</form>
	
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
