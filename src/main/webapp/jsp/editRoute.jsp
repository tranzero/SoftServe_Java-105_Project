<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<script src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/editRoute.js"></script>

	<h2 align="center"><spring:message code="label.navigation.editRoute"/></h2>
	<form:form action="editRoutePage" method="POST" commandName="routes">
	<div>
	
		<table class="form" >
			<tbody>
			<tr>
				<td><input id="routeId" type="hidden" name="routeId" value="${route.getRouteId()}">
				</td>
			</tr>
			
			<tr>
				<td><spring:message code="label.routes.routecode"/></td>
				<td><input type="text" name="routeCode" value="${route.getRouteCode()}"></td>
			</tr>
			
			<tr>
				<td><spring:message code="label.lines.linename"/></td>
				<td><input id="lineName" type="text" name="lineName" value="${route.getLineId().getLineName()}"></td>
			</tr>
			
			<tr>
				<td><spring:message code="label.routes.stationStart"/></td>
				<td><input id="stationStart" type="text" name="stationStart" value="${route.getStationStartId().getStationName()}"></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.stationEnd"/></td>
				<td><input id="stationEnd" type="text" name="stationEnd" value="${route.getStationEndId().getStationName()}"></td>
			</tr>
			</tbody>
		</table>
				
	</div>
		

		<input name="control" type="submit" value="<spring:message code="label.update"/>" />
		<input type="button" value="<spring:message code="label.cancel"/>"
			onclick="window.location='/SoftServe_Java-105/routesAllEdit';">	
	</form:form>
	<script>
		$(function(){
			getLineList();
		});
		$(function(){
			getStationStartOnLineList();
		});
	</script>
</section>