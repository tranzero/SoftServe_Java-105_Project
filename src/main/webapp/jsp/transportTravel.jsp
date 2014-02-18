<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Transport find</h2>
	<form:form action="transportTravel" method="get">
			From: <input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder="Sknyliv" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	<div id="result">
		<%-- Results --%>
		<c:if test="${!empty TransportTravelList}">
			<hr />
			<table>
				<tr>
					<td>Number</td>
					<td><a
						href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&orderBy=1&submit=Find">Station
							/ Stop</a></td>
					<td><a
						href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&orderBy=2&submit=Find">Transport
							Number</a></td>
					<td>Departure / Arrival time</td>
					<td><a
						href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&orderBy=3&submit=Find">Duration</a></td>
				</tr>
				<c:forEach var="transport" items="${TransportTravelList}">
					<tr>
						<td id="generate"></td>
						<td>${transport.getLineName()}</td>
						<td>${transport.getTransport().getTransportCode()}</td>
						<td>dep ${transport.getDepartureTime()}<br />arr
							${transport.getArrivalTime()}
						</td>
						<td>${transport.getDuration()}</td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if
			test="${empty TransportTravelList && not empty param.stationName1 && not empty param.stationName2}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>

		<c:if
			test="${not empty param.stationName1 && not empty param.stationName2}">
			<div class="pagination">
				<ul class="bootpag">
					<c:if test="${pageNumber>1}">
						<li class="prev"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=1&resultsPerPage=${resultsPerPage}">
								« </a></li>
						<li class="prev"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
								href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${i}&resultsPerPage=${resultsPerPage}">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
	<c:if
		test="${not empty param.stationName1 && not empty param.stationName2}">
		<script>
			function showTransportPage(stationName1_, stationName2_,
					pageNumber_, resultsPerPage_) {

				if (stationName1_ == "" || stationName2_ == "") {
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
									url : "transportTravelPage",
									data : {
										stationName1 : stationName1_,
										stationName2 : stationName2_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

			$(window).load(
					function() {
						showTransportPage("${param.stationName1}",
								"${param.stationName2}", "${pageNumber}",
								"${resultsPerPage}");
					});
		</script>
	</c:if>

</section>
