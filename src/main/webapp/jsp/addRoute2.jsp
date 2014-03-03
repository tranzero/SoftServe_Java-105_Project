<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addRoute"/></h2>
	<form:form action="addRoutePage" method="POST" commandName="routes">

			<tr>
				<td><form:label path="routeCode" ><spring:message code="label.routes.routecode"/></form:label></td>
				<td><form:input path="routeCode" onfocus="checkpostal()" /></td>
				<td><form:errors path="routeCode" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="lines" ><spring:message code="label.lines.linename"/></form:label></td>
				<td><form:input path="lines" onfocus="checkpostal()" /></td>
				<td><form:errors path="lines" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="stationStart" ><spring:message code="label.lines.linename"/></form:label></td>
				<td><form:input path="stationStart" onfocus="checkpostal()" /></td>
				<td><form:errors path="stationStart" cssClass="error"/></td>
			</tr>
			<tr>
				<td><form:label path="stationEnd" ><spring:message code="label.lines.linename"/></form:label></td>
				<td><form:input path="stationEnd" onfocus="checkpostal()" /></td>
				<td><form:errors path="stationEnd" cssClass="error"/></td>
			</tr>

				<input type="submit" value="<spring:message code="label.add"/>" />
				<input type="button" value="<spring:message code="label.cancel"/>" 
					onclick="window.location='/SoftServe_Java-105/routesAllEdit';">
	</form:form>

</section>