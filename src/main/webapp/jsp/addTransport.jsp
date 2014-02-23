<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/addTransportValidation.js"></script>

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

<section id="content">
	<h2 align="center"><spring:message code="label.navigation.addTransport"/></h2>
	
		<form:form id="addTransport.htm" method="POST" action="addTransport.htm" commandName="transport">
			<form:errors path="*" cssClass="errorblock" element="div" />

			<p><spring:message code="label.transport.transportcode"/></p>
			<p><form:input type="text" path="transportCode" name="transportCode" onfocus="checkpostal()" />
			<form:errors path="transportCode" cssClass="error"/>
		
			<p><spring:message code="label.transport.starttime"/></p>
			<p><form:input id="startTime" type="text" path="startTime" class="startTime" placeholder="HH:MM:SS" />
			<form:errors path="startTime" cssClass="error"/></p>
	
			<p><spring:message code="label.transport.seatclass1"/></p>
			<p><form:input id="seatclass1" path="seatclass1" name="seatclass1"/>
			<form:errors path="seatclass1" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass2"/></p>
			<p><form:input id="seatclass2" path="seatclass2" name="seatclass2" />
			<form:errors path="seatclass2" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.seatclass3"/></p>
			<p><form:input  id="seatclass3" path="seatclass3" name="seatclass3" />
			<form:errors path="seatclass3" cssClass="error"/></p>
			
			<p><spring:message code="label.transport.genprice"/></p>
			<p><form:input id="genPrice" path="genPrice" name="genPrice"/>
			<form:errors path="genPrice" cssClass="error"/></p>
	
			<spring:message code="label.routes.routecode"/>
			
			<p><form:errors path="routes" cssClass="error"/>
			<form:select path="routes" name="routes">
				<c:forEach var="route" items="${routesList}">
					<option value="${route.getRouteId()}">
						${route.getRouteCode()}&emsp; ${route.getLineId().getLineName()}</option>
				</c:forEach>
			</form:select>
				
		<form:input path="" type="submit" value="Add" />
		
	</form:form>
	
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
