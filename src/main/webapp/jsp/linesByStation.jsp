<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Lines Search</h2>
	<form:form action="linesByStation" id="linesByStation" method="get">
		Enter station name :
		<c:if test="${not empty param.stationName}">
			<input type="text" name="stationName" value="${param.stationName}" />
		</c:if>
		<c:if test="${empty param.stationName}">
			<input type="text" name="stationName"/>
		</c:if>
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	<div id="result">
		<c:if test="${!empty linesByStationList}">
			<hr />
		<table>
		<thead>
			<tr>
				<td>Number</td>
				<td><spring:message code="label.lines.linename" /> <a
					href="javascript:void(0);"
					onclick="showLinesByStationPage('${param.stationName}',${pageNumber},${resultsPerPage}, '1')"><img
						alt="^" src="resources/images/downarrow.png"></a> <a
					href="javascript:void(0);"
					onclick="showLinesByStationPage('${param.stationName}',${pageNumber},${resultsPerPage}, '2')"><img
						alt="^" src="resources/images/uparrow.png"></a></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="lines" items="${linesByStationList}">
			<tr>
				<td id="generate"></td>
				<td>${lines.getLineName()}</td>
				<td><a href="stationsoncertainline/${lines.getLineName()}">Show
						stations</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
			<hr />
		</c:if>
		<c:if test="${empty linesByStationList && not empty param.stationName}">
			<p>No results.</p>
		</c:if>
		<c:if test="${not empty param.stationName}">
			<div class="pagination">
				<ul class="bootpag">
					<c:if test="${pageNumber>1}">
						<li class="prev"><a
							href="?stationName=${param.stationName}&pageNumber=1&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}">
								« </a></li>
						<li class="prev"><a
							href="?stationName=${param.stationName}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}">
								<spring:message code="label.prev" />
						</a></li>
					</c:if>
					<c:if test="${pageNumber==1}">
						<li class="prev disabled"><a href="javascript:void(0);">
								« </a></li>
						<li class="prev disabled"><a href="javascript:void(0);">
								<spring:message code="label.prev" />
						</a></li>
					</c:if>
					<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
						varStatus="status">
						<c:if test="${pageNumber!=i}">
							<li><a
								href="?stationName=${param.stationName}&pageNumber=${i}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?stationName=${param.stationName}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?stationName=${param.stationName}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}">
								» </a></li>
					</c:if>
					<c:if test="${pageNumber==maxPages}">
						<li class="next disabled"><a href="javascript:void(0);">
								<spring:message code="label.next" />
						</a></li>
						<li class="next disabled"><a href="javascript:void(0);">
								» </a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
	<c:if test="${not empty param.stationName}">
		<script>
			function showLinesByStationPage(stationName_, pageNumber_,
					resultsPerPage_, sortOrder_) {

				if (stationName_ == "") {
					return;
				}
				$
						.ajax(
								{
									async : true,
									beforeSend : function() {
										$("div#result")
												.html(
														'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
									},
									type : "GET",
									url : "linesByStationResult",
									data : {
										stationName : stationName_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_,
										sortOrder: sortOrder_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

			$(window).load(
					function() {
						showLinesByStationPage("${param.stationName}",
								"${pageNumber}", "${resultsPerPage}", "${param.sortOrder}");
					});
		</script>

	</c:if>

</section>
