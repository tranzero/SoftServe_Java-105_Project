<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	function confirm_delete() {
		return confirm('Are you sure?');
	}
</script>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.stations" />
	</h2>
	
	<table border="1">
		<thead>
			<tr >
				<td width="111" height="37"><a class="button" href="addStation"><spring:message code="label.navigation.addStation" /></a></td>
			</tr>
			<tr>
				<th align="center"><spring:message code="label.stations.number" /></th>
				<th><spring:message code="label.stations.stationcode" /></th>
				<th><spring:message code="label.stations.stationname" /></th>
		
			</tr>
		</thead>
		<tbody>
			<c:forEach var="station" items="${stationsList}">
				<tr>
					<td align="center" id="generate"></td>
					<td align="center">${station.getStationCode()}</td>
					<td align="center">${station.getStationName()}</td>
					<td><a
						href="stationEdit/${station.getStationId()}"><spring:message
							code="label.stations.edit" /></a></td>
					<td><a href="delete/${station.getStationId()}"
						onclick="return confirm_delete()"><spring:message
							code="label.stations.delete" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
