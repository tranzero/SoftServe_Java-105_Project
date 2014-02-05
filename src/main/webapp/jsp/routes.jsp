<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Routes</title>
</head>

<body>-->
<section id="content">
<div id="routesPage">
	<div id="routesMenu">
	
		<b>Return Routes of transports that are arriving to certain station during certain times</b>
		<form action="routesSearch" method="get">
			idStationArriving: <input type="text" name="idStationArriving"></input>
			timeArrivalMin: <input type="text" name="timeArrivalMin"></input>
			timeArrivalMax: <input type="text" name="timeArrivalMax"></input>
			<input type="submit" name="submit" value ="Search"></input>
		</form>
	</div>
	<hr />
	<table class='tableRoutes'>
		<thead>
			<tr>
				<th>routeId</th>
				<th>LineId</th>
				<th>RouteCode</th>
				<th>StartTime</th>
			</tr>
		</thead>
	<tbody>
		<c:forEach var="routes" items="${RoutesList}">
			<tr>
				<td>${routesArriving.getRouteId()}</td>
				<td>${routesArriving.getLineId().getLineName()}</td>
				<td>${routesArriving.getRouteCode()}</td>
				<td>${routesArriving.getStartTime()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


</div>
</section><!-- 
</body>
</html> -->