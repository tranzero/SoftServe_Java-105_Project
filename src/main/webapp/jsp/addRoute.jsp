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
	<script type="text/javascript" src="resources/js/addRoute.js"></script>
	
	<h2 align="center"><spring:message code="label.navigation.addRoute"/></h2>
	<form:form action="addRoutePage" method="POST" commandName="routes">

	<div>
		
		<table class="form" >
			<tbody>
			<tr>
				<td><spring:message code="label.routes.routecode"/></td>
				<td><input type="text" name="routeCode"></td>
			</tr>
			<tr>
				<td><spring:message code="label.lines.linename"/></td>
				<td><input id="lineName" type="text" name="lineName"></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.stationStart"/></td>
				<td><input id="stationStart" type="text" name="stationStart"></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.stationEnd"/></td>
				<td><input id="stationEnd" type="text" name="stationEnd"></td>
			</tr>
			</tbody>
		</table>
				
	</div>
		
				<input type="submit" value="<spring:message code="label.add"/>" />
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
