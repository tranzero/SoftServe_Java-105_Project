<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<section id="content">
<div id="routesPage">

	<div id="routesMenu">
		<form action="routesFind" method="get">
			timeMin: <input type="text" name="timeMin"></input>
			timeMax: <input type="text" name="timeMax"></input>
			Arrive:<input name="findBy" type="radio" value="findByArr" checked>
            Departure<input name="findBy" type="radio" value="findByDep">
            <p/>
			nameStation: <input type="text" name="nameStation"></input>
			
			<input class="button" type="submit" name="submit" value ="Find"></input>
		</form>
	</div>
	<%-- Results --%>
		<c:if test="${!empty RouteTripsList}">
		<hr/>
		<h2 align="center">
			List of Routes
		</h2>
				<table class='tableRouteTrips'>
		<thead>
			<tr>
				<th align="center">LineName</th>
				<th align="center">RouteCode</th>
				<th align="center">StartTime</th>
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
