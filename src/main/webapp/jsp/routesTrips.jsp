<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section id="content">
<div id="routesPage">

	<h2 align="center">
		<spring:message code="label.navigation.routes" />
	</h2>
	<div id="routesMenu">
		<form action="routesFind" method="get">
			<spring:message code="label.navigation.minTime" /> <input type="text" name="timeMin" placeholder="1:00:15" ></input>
			<spring:message code="label.navigation.maxTime" /> <input type="text" name="timeMax" placeholder="23:00:30" ></input>
			<spring:message code="label.navigation.arrival" /> <input name="findBy" type="radio" value="findByArr" checked>
            <spring:message code="label.navigation.departure" /> <input name="findBy" type="radio" value="findByDep">
            <p/>
			<spring:message code="label.stations.stationname" /> <input type="text" name="nameStation " placeholder="Pisochne" ></input>

			<input class="button" type="submit" name="submit" value =<spring:message code="label.navigation.findrouts" />></input>
		</form>
	</div>
	<%-- Results --%>
		<c:if test="${!empty RouteTripsList}">
		<hr/>
				<table class='tableRouteTrips'>
		<thead>
			<tr>
				<th align="center"><spring:message code="label.lines.linename" /></th>
				<th align="center"><spring:message code="label.routes.routecode" /></th>
				<th align="center"><spring:message code="label.navigation.Time" /></th>
			</tr>
		</thead>
	<tbody>
		<c:forEach var="routetrip" items="${RouteTripsList}">
			<tr>
				<td align="center">${routetrip.getRoute().getLineId().getLineName()}</td>
				<td align="center">${routetrip.getRoute().getRouteCode()}</td>
				<td align="center">${routetrip.getStartTime()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
			<hr />
		</c:if>
		<c:if test="${empty RouteTripsList && not empty param.nameStation && not empty param.timeMin && not empty param.timeMax}">
			<hr />
			<b>Return no data</b>
			<h3>No results was found</h3>
			<hr />
		</c:if>
</div>
</section>
