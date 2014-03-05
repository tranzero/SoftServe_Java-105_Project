<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
		<form:form action="addTransport.htm" method="POST" modelAttribute="transport">

 		<table class="form" >
			<tbody>
			<tr>
				<td><form:label path="transportCode" ><spring:message code="label.transport.transportcode"/>
					<em class="star">*</em>:</form:label></td>
				<td><form:input path="transportCode" onfocus="checkpostal()" /></td>
				<td><form:errors path="transportCode" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.starttime"/><em class="star">*</em>:</td>
				<td><form:input id="startTime" path="startTime" cssClass="startTime" placeholder="HH:MM:SS" /></td>
				<td><form:errors path="startTime" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.routecode"/><em class="star">*</em>:</td>
				<td><p><form:select path="routes" >
					<form:option value="-" label="--Please Select Routes"/>
					<form:options items="${routesList}"  itemValue="routeId" itemLabel="routeCode"/>
				</form:select></td>
				<td><form:errors path="routes" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass1"/><em class="star">**</em>:</td>
				<td><form:input path="seatclass1"/></td>
				<td><form:errors path="seatclass1" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass2"/><em class="star">**</em>:</td>
				<td><form:input path="seatclass2" /></td>
				<td><form:errors path="seatclass2" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass3"/><em class="star">**</em>:</td>
				<td><form:input path="seatclass3"/></td>
				<td><form:errors path="seatclass3" cssClass="error"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.genprice"/><em class="star">*</em>:</td>
				<td><form:input path="genPrice"/></td>
				<td><form:errors path="genPrice" cssClass="error"/></td>
			</tr>
			</tbody>
		</table>
				<input type="submit" value="<spring:message code="label.add"/>" />
				<input type="button" value="<spring:message code="label.cancel"/>" 
					onclick="window.location='/SoftServe_Java-105/transport';">
	</form:form>
	
		<p><em class="star">*</em> — <spring:message code="label.transport.star"/>
		<p><em class="star">**</em> — <spring:message code="label.transport.stars"/>
	
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
