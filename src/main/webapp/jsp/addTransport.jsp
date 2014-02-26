<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<!-- <script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/addTransportValidation.js"></script> -->

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
		<form:form id="transports" method="POST" action="addTransport.htm" commandName="transport">
<%-- 			<form:errors path="*" cssClass="errorblock" element="div" />--%>
 		<table class="form" >
			<tbody>
			<tr>
				<td><spring:message code="label.transport.transportcode"/></td>
				<td><form:input id="transportCode" type="text" path="transportCode" name="transportCode" onfocus="checkpostal()" /></td>
				<td><form:errors path="transportCode" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.starttime"/></td>
				<td><form:input id="startTime" type="text" path="startTime" name="startTime"
					cssClass="startTime" placeholder="HH:MM:SS" /></td>
				<td><form:errors path="startTime" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.routecode"/></td>
				<td><form:select path="routes" name="routes" >
					<c:forEach var="route" items="${routesList}">
						<option value="${route.getRouteId()}">
							${route.getRouteCode()}&emsp;${route.getLineId().getLineName()}</option>
					</c:forEach>
				</form:select></td>
				<td><form:errors path="routes" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass1"/></td>
				<td><form:input id="seatclass1" path="seatclass1"/></td>
				<td><form:errors path="seatclass1" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass2"/></td>
				<td><form:input id="seatclass2" path="seatclass2" /></td>
				<td><form:errors path="seatclass2" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass3"/></td>
				<td><form:input  id="seatclass3" path="seatclass3"/></td>
				<td><form:errors path="seatclass3" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.genprice"/></td>
				<td><form:input id="genPrice" path="genPrice"/></td>
				<td><form:errors path="genPrice" cssClass="error"/></td>
			</tr>
			<tr>
			<td align="center"><form:input path="" type="submit" value="Add" />
		
			<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/transport';"></td>
				<td></td><td></td>
			</tr>
			</tbody>
		</table>
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
