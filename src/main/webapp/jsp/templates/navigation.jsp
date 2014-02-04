<%@page contentType = "text/html" pageEncoding ="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id = "navigation" >
<h2><spring:message code="label.navigation.title"/>[WIP]</h2>

<div class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.timetable"/></span>
	<span class = "navigationPoint"></span>
</div>

<div class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.orders"/></span>
	<span class = "navigationPoint"></span>
</div>

<div class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.editing"/></span>
	<span class = "navigationPoint"></span>
</div>
<div class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.users"/></span>
	<span class = "navigationPoint"></span>
</div>


</div>