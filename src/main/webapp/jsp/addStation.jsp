<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/addstations-valid.js"></script>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addStation"/></h2>
	<hr />

	<form id="addStation" method="post" name="/addStation" action="addstat"
		style="text-align: center">
		<div>
			<p><spring:message code="label.stations.stationcode" /></p>
			<input id="stationCode" type="text" name="stationCode">

			<p><spring:message code="label.stations.stationname" /></p>
			<input id="stationName" type="text" name="stationName"> <br>
			<p>
				<input type="submit" value="<spring:message code="label.navigation.addStation"/>"> 
				<input type="button" value="<spring:message code="label.stations.cancel"/>" onclick="window.location='stations';">
			</p>
		</div>
	</form>
</section>