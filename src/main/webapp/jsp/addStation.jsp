<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.addStation" />
	</h2>
	<hr />

	<form:form id="stations" method="post" action="addStation"
		commandName="station">
		<table class="form">
			<tbody>
				<tr>
					<td align="center"><spring:message
							code="label.stations.stationcode" /></td>
					<td align="center"><form:input path="stationCode"
							onfocus="checkpostal()" /></td>
					<td align="center"><form:errors path="stationCode"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td align="center"><spring:message
							code="label.stations.stationname" /></td>
					<td align="center"><form:input path="stationName"
							onfocus="checkpostal()" /></td>
					<td align="center"><form:errors path="stationName"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td></td>
					<td align="center" colspan="3"><form:input path=""
							type="submit" value="Add" /> <input type="button"
						value="<spring:message code="label.stations.cancel"/>"
						onclick="window.location='/SoftServe_Java-105/stations';"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</section>