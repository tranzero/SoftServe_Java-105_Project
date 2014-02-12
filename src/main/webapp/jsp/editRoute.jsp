<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h1 align="center">Edit Transport</h1>
	<form id="editTRoute" method="post" name="route">
		<div>
			<p><input id="routeId" type="hidden" name="routeId"
				value="${route.getRouteId()}"></p>

			<p><spring:message code="label.routes.routecode"/></p>
			<p><input id="routes" type="text" name="routeCode"
				value="${route.getRouteCode()}" /></p>
				
			<p><spring:message code="label.lines.linename" /></p>
			<p><input id="routes" type="text" name="lineName"
				value="${route.getLineId().getLineName()}" /></p>		

			<p><input type="submit" value="Update">&emsp;
				<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/routesAllEdit';"></p>

		</div>
	</form>
</section>