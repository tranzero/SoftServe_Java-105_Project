<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.stationsForUsers" />
	</h2>

	<table style="align: center">
		<thead>
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
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">
		<ul class="bootpag">
			<c:if test="${pageNumber>1}">
				<li class="prev"><a href="javascript:void(0);"
					onclick="showStationPage(1, ${resultsPerPage})"> « </a></li>
				<li class="prev"><a href="javascript:void(0);"
					onclick="showStationPage(${pageNumber-1},${resultsPerPage})"> <spring:message
							code="label.prev" />
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
						onclick="showStationPage(${i},${resultsPerPage})"> ${i} </a></li>
				</c:if>
				<c:if test="${pageNumber==i}">
					<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
				</c:if>
			</c:forEach>

			<c:if test="${pageNumber<maxPages}">
				<li class="next"><a href="javascript:void(0);"
					onclick="showStationPage(${pageNumber+1},${resultsPerPage})"> <spring:message
							code="label.next" />
				</a></li>
				<li class="next"><a href="javascript:void(0);"
					onclick="showStationPage(${maxPages},${resultsPerPage})"> » </a></li>
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