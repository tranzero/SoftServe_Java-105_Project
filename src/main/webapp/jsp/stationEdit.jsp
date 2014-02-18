<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/editstation-valid.js"></script>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.stationEdit"/></h2>
	<hr />

	<form id="stationEdit" method="post" name="statEdit"
		style="text-align: center">
		<input id="stationId" type="hidden" name="stationId"
			value="${station.getStationId()}">

		<div>
			<p><spring:message code="label.stations.stationcode" /></p>
			<input id="stationCode" type="text" name="stationCode"
				value="${station.getStationCode()}">

			<p><spring:message code="label.stations.stationname" /></p>
			<input id="stationName" type="text" name="stationName"
				value="${station.getStationName()}">

			<p>
				<input type="submit" value="<spring:message code="label.stations.edit"/>"> 
				<input type="button" value="<spring:message code="label.stations.cancel"/>" onclick="window.location='/SoftServe_Java-105/stations';">
			</p>
		</div>

	</form>
</section>