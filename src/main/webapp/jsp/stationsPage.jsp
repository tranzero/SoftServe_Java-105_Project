<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<script type="text/javascript">
	function confirm_delete() {
		return confirm('Are you sure?');
	}
</script>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.stations" />
	</h2>

	<table border="1" style="align: center">
		<thead>
			<tr>
				<td align="center" height="30"><a href="addStation"> <input
						id="edit" type="button" name="edit"
						value="<spring:message code="label.navigation.addStation"/>">
				</a></td>
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
					<td align="center"><a
						href="stationEdit/${station.getStationId()}"> <input id="edit"
							type="button" name="edit"
							value="<spring:message code="label.stations.edit"/>">
					</a></td>
					<td align="center"><a href="delete/${station.getStationId()}"><input
							id="delete" type="button" name="delete"
							onclick="return confirm_delete()"
							value="<spring:message code="label.stations.delete"/>"> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
		<ul class="bootpag">
			<c:if test="${pageNumber>1}">
				<li class="prev"><a href="javascript:void(0);"
					onclick="showStationsManagerPage(1, ${resultsPerPage})"> « </a></li>
				<li class="prev"><a href="javascript:void(0);"
					onclick="showStationsManagerPage(${pageNumber-1},${resultsPerPage})">
						<spring:message code="label.prev" />
				</a></li>
			</c:if>
			<c:if test="${pageNumber==1}">
				<li class="prev disabled"><a href="javascript:void(0);"> «
				</a></li>
				<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
							code="label.prev" />
				</a></li>
			</c:if>
			<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
				varStatus="status">
				<c:if test="${pageNumber!=i}">
					<li><a href="javascript:void(0);"
						onclick="showStationsManagerPage(${i},${resultsPerPage})">
							${i} </a></li>
				</c:if>
				<c:if test="${pageNumber==i}">
					<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
				</c:if>
			</c:forEach>

			<c:if test="${pageNumber<maxPages}">
				<li class="next"><a href="javascript:void(0);"
					onclick="showStationsManagerPage(${pageNumber+1},${resultsPerPage})">
						<spring:message code="label.next" />
				</a></li>
				<li class="next"><a href="javascript:void(0);"
					onclick="showStationsManagerPage(${maxPages},${resultsPerPage})">
						» </a></li>
			</c:if>
			<c:if test="${pageNumber==maxPages}">
				<li class="next disabled"><a href="javascript:void(0);"> <spring:message
							code="label.next" />
				</a></li>
				<li class="next disabled"><a href="javascript:void(0);"> »
				</a></li>
			</c:if>
		</ul>
	</div>
</section>