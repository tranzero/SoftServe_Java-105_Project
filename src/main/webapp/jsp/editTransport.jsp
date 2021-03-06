<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<section id="content">
	<h1 align="center"><spring:message code="label.transport.editTransport"/></h1>

	<form:form action="addTransport.htm" method="GET" modelAttribute="transport">
	
			<form:hidden path="transportId" />
			
		<table class="form">
			<tbody>
				<tr>
					<td><spring:message code="label.transport.transportcode" /><em class="star">*</em>:</td>
					<td><form:input id="transportCode" path="transportCode" onfocus="checkpostal()" /></td>
					<td><form:errors path="transportCode" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.starttime" /><em class="star">*</em>:</td>
					<td><form:input id="startTime" path="startTime" cssClass="startTime"
						placeholder="HH:MM:SS" /></td>
					<td><form:errors path="startTime" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.routes.routecode" /><em class="star">*</em>:</td>
					<td><form:select id="routes" path="routes" class="selectRoutes">
						<form:options items="${routesList}" itemValue="routeId"	itemLabel="routeCode" />
					</form:select></td>
					<td><form:errors path="routes" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass1" /><em class="star">**</em>:</td>
					<td><form:input id="seatclass1" path="seatclass1" /></td>
					<td><form:errors path="seatclass1" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass2" /><em class="star">**</em>:</td>
					<td><form:input id="seatclass2" path="seatclass2" /></td>
					<td><form:errors path="seatclass2" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.seatclass3" /><em class="star">**</em>:</td>
					<td><form:input id="seatclass3" path="seatclass3" /></td>
					<td><form:errors path="seatclass3" cssClass="error" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.transport.genprice" /><em class="star">*</em>:</td>
					<td><form:input id="genPrice" path="genPrice" /></td>
					<td><form:errors path="genPrice" cssClass="error" /></td>
				</tr>
			</tbody>
		</table>
		<div class="pad_top_bottom">
			<input name="control" type="submit" value="<spring:message code="label.update"/>" />
			<input type="button" value="<spring:message code="label.cancel"/>"
				onclick="window.location='/SoftServe_Java-105/transportListManage';">
		</div>

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
