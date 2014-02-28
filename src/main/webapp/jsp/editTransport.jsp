<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<section id="content">
	<h1 align="center"><spring:message code="label.transport.editTransport"/></h1>

	<form:form action="addTransport.htm" method="POST" commandName="transport">
	
			<form:hidden path="transportId" />
			
		<table class="form">
			<tbody>
				<tr>
					<td><spring:message code="label.transport.transportcode" /></td>
					<td><form:input path="transportCode" onfocus="checkpostal()" /></td>
					<td><form:errors path="transportCode" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.starttime" /></td>
					<td><form:input id="startTime" path="startTime" cssClass="startTime"
						placeholder="HH:MM:SS" /></td>
					<td><form:errors path="startTime" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.routes.routecode" /></td>
					<td><form:select path="routes">
						<form:options items="${routesList}" itemValue="routeId"	itemLabel="routeCode" />
					</form:select></td>
					<td><form:errors path="routes" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass1" /></td>
					<td><form:input path="seatclass1" /></td>
					<td><form:errors path="seatclass1" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass2" /></td>
					<td><form:input path="seatclass2" /></td>
					<td><form:errors path="seatclass2" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass3" /></td>
					<td><form:input path="seatclass3" /></td>
					<td><form:errors path="seatclass3" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.genprice" /></td>
					<td><form:input path="genPrice" /></td>
					<td><form:errors path="genPrice" cssClass="error" /></td>
				</tr>
			</tbody>
		</table>

		<input name="control" type="submit" value="<spring:message code="label.update"/>" />
		<input type="button" value="<spring:message code="label.cancel"/>"
			onclick="window.location='/SoftServe_Java-105/transport';">

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
