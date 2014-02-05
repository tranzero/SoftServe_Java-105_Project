<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
<div id="context">
	<h2>Transport adding</h2>

	<form action="addTransport.htm" " method="post" name="transport">
		<table>
			<!-- <tr>
				<td>Transport ID</td>
				<td><input type="text" name="transportId"></td>
			</tr> -->
			<tr>
				<td>Transport Code</td>
				<td><input type="text" name="transportCode"></td>
			</tr>
			<tr>
				<td>Start Time</td>
				<td><input type="text" name="startTime"></td>
			</tr>
			<tr>
				<td>Routes ID</td>
				<td><input type="text" name="routes"></td>
			</tr>
		</table>
		<input type="submit" value="Add Transport">
	</form>

<%-- 		 <form:form method="POST" action="addTransport.htm"	commandName="transport">
		<table>
			<tr>
				<td><form:label path="transportId">Transport ID</form:label></td>
				<td><form:input path="transportId" /></td>
			</tr>
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
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form:form> --%>
</div>
