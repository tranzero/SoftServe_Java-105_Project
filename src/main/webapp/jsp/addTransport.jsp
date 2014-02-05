<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
	<form action="addTransport.htm" method="post" name="transport">
		<table>
			<tr>
				<td><spring:message code="label.transport.transportcode"/></td>
				<td><input type="text" name="transportCode"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.starttime"/></td>
				<td><input type="text" name="startTime"></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.routecode"/></td>
				<td><input type="text" name="routes"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass1"/></td>
				<td><input type="text" name="seatclass1"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass2"/></td>
				<td><input type="text" name="seatclass2"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass3"/></td>
				<td><input type="text" name="seatclass3"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.genprice"/></td>
				<td><input type="text" name="genprice"></td>
			</tr>
		</table>
		<input type="submit" value="Add Transport">
	</form>

<%-- 	<form:form method="POST" action="addTransport.htm"
		commandName="transport">
		<table>
			<tr>
				<td><form:label path="transportCode">Transport Code</form:label></td>
				<td><form:input path="transportCode" /></td>
			</tr>
			<tr>
				<td><form:label path="startTime">Start Time</form:label></td>
				<td><form:input path="startTime" /></td>
			</tr>
			<tr>
				<td><form:label path="routes">Routes ID</form:label></td>
				<td><form:input path="routes" /></td>
			<tr>
				<td><form:label path="seatclass1">Seat Class 1</form:label></td>
				<td><form:input path="seatclass1" /></td>
			</tr>
			<tr>
				<td><form:label path="seatclass2">Seat Class 2</form:label></td>
				<td><form:input path="seatclass2" /></td>
			</tr>
			<tr>
				<td><form:label path="seatclass3">Seat Class 3</form:label></td>
				<td><form:input path="seatclass3" /></td>
			</tr>
			<tr>
				<td><form:label path="genprice">General Price</form:label></td>
				<td><form:input path="genprice" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form:form> --%>
</section>
