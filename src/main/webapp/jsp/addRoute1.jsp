<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<h2 align="center"><spring:message code="label.navigation.addRoute"/></h2>
	
	<form action="addRoute" method="post" name="route">
		<div>
				<p><spring:message code="label.routes.routecode"/></p>
				<p><input type="text" name="routeCode"></p>
				
				<p><spring:message code="label.lines.linename"/></p>
				<p><input type="text" name="lineName"></p>
		</div>
		<input type="submit" value="<spring:message code="label.navigation.addRoute"/>">
	</form>
	<script>

	</script>
</section>