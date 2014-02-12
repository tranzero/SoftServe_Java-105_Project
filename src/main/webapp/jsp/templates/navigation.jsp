<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--<div id = "navigation" >
<h2><spring:message code="label.navigation.title"/>[WIP]</h2> --%>
<nav id="navigation">
	<div id="container">
		<ul class="menu">
			<li><a href="/SoftServe_Java-105/mainpage"><spring:message
						code="label.navigation.news" /></a></li>
			<li><spring:message code="label.navigation.timetable" />
				<ul class="submenu">
					<li><a href="/SoftServe_Java-105/stationsForUsers"><spring:message
								code="label.navigation.stationsForUsers" /></a></li>
					<li><a href="/SoftServe_Java-105/trips"><spring:message
								code="label.navigation.trips" /></a></li>
					<li><a href="/SoftServe_Java-105/transportTravel"><spring:message
								code="label.navigation.transportTravel" /></a></li>
					<li><a href="/SoftServe_Java-105/trips"><spring:message
								code="label.navigation.trips" /></a></li>
					<li><a href="/SoftServe_Java-105/transportView"><spring:message
								code="label.navigation.transport" /></a></li>
					<li><a href="/SoftServe_Java-105/routes"><spring:message
								code="label.navigation.route" /></a></li>
					<li><a href="/SoftServe_Java-105/routesTrips"><spring:message
								code="label.navigation.routes" /></a></li>

				</ul></li>
			<li><spring:message code="label.navigation.orders" /></li>
			<li><spring:message code="label.navigation.editing" />
				<ul class="submenu">
					<li><a href="/SoftServe_Java-105/addnews"><spring:message
								code="label.navigation.addnews" /></a></li>
					<li><a href="/SoftServe_Java-105/stations"><spring:message
								code="label.navigation.stations" /></a></li>
					<li><a href="/SoftServe_Java-105/allLines"><spring:message
								code="label.navigation.allLines" /></a></li>
					<li><a href="/SoftServe_Java-105/tripsmanager"><spring:message
								code="label.navigation.managetrips" /></a></li>
					<li><a href="/SoftServe_Java-105/linesbystation"><spring:message
								code="label.navigation.linesbystation" /></a></li>
					<li><a href="/SoftServe_Java-105/addTrip"><spring:message
								code="label.navigation.addtrips" /></a></li>
					<li><a href="/SoftServe_Java-105/transport"><spring:message
								code="label.navigation.transportManage" /></a></li>
				</ul></li>
			<li><spring:message code="label.navigation.users" />
				<ul class="submenu">
					<li><a href="/SoftServe_Java-105/userlist"><spring:message
								code="label.navigation.userlist" /></a></li>
					<li><a href="/SoftServe_Java-105/userlist2"><spring:message
								code="label.navigation.userlist" />2</a></li>

				</ul></li>
		</ul>
	</div>
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
	<span class = "navigationPoint"><a href="allLines"><spring:message code="label.navigation.addLines"/></a></span>
	<span class = "navigationPoint"><a href="linesbystation"><spring:message code="label.navigation.linesbystation"/></a></span>
</nav>
<nav class="navigationGroup">
	<span class="navigationHeader"><spring:message code="label.navigation.users"/></span>
	<span class = "navigationPoint"><a href="userlist"><spring:message code="label.navigation.userlist"/></a></span>
</nav>
--%>

<%-- </div> --%>
