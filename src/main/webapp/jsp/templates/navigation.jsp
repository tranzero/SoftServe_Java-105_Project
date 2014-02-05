<%@page contentType = "text/html" pageEncoding ="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--<div id = "navigation" >
<h2><spring:message code="label.navigation.title"/>[WIP]</h2> --%>
<nav id="navigation">
	<ul class="menu">
		<li><a href="mainpage"><spring:message code="label.navigation.news"/></a></li>
		<li><spring:message code="label.navigation.timetable"/>
		<ul class="submenu">
			<li><a href="stations"><spring:message code="label.navigation.stations"/></a></li>
			<li><a href="routes"><spring:message code="label.navigation.routes"/></a></li>
			<li><a href="transport"><spring:message code="label.navigation.transport"/></a></li>
			<li><a href="transportTravel"><spring:message code="label.navigation.transportTravel"/></a></li>
		</ul>
		</li>
		<li><spring:message code="label.navigation.orders"/></li>
		<li><spring:message code="label.navigation.editing"/>
		<ul class="submenu">
			<li><a href="addnews"><spring:message code="label.navigation.addnews"/></a></li>
			<li><a href="addStation"><spring:message code="label.navigation.addStation"/></a></li>
			<li><a href="addLines"><spring:message code="label.navigation.addLines"/></a></li>
			<li><a href="linesbystation"><spring:message code="label.navigation.linesbystation"/></a></li>
		</ul>
		</li>
		<li><spring:message code="label.navigation.users"/>
		<ul class="submenu">
			<li><a href="userlist"><spring:message code="label.navigation.userlist"/></a></li>
		</ul>
		</li>
	</ul>
</nav>
<%--
<span class = "navigationPoint"><a href="mainpage"><spring:message code="label.navigation.news"/></a></span>

<nav class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.timetable"/></span>
	<span class = "navigationPoint"><a href="stations"><spring:message code="label.navigation.stations"/></a></span>
	<span class = "navigationPoint"><a href="routes"><spring:message code="label.navigation.routes"/></a></span>
	<span class = "navigationPoint"><a href="transport"><spring:message code="label.navigation.transport"/></a></span>
	<span class = "navigationPoint"><a href="transportTravel"><spring:message code="label.navigation.transportTravel"/></a></span>
</nav>

<nav class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.orders"/></span>
</nav>

<nav class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.editing"/></span>
	<span class = "navigationPoint"><a href="addnews"><spring:message code="label.navigation.addnews"/></a></span>
	<span class = "navigationPoint"><a href="addStation"><spring:message code="label.navigation.addStation"/></a></span>
	<span class = "navigationPoint"><a href="addLines"><spring:message code="label.navigation.addLines"/></a></span>
	<span class = "navigationPoint"><a href="linesbystation"><spring:message code="label.navigation.linesbystation"/></a></span>
</nav>
<nav class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.users"/></span>
	<span class = "navigationPoint"><a href="userlist"><spring:message code="label.navigation.userlist"/></a></span>
</nav>
--%>

<%-- </div> --%>