<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>
		<spring:message code="label.transport.find" />
	</h2>
	<form:form action="transportTravel" method="get">
		<spring:message code="label.from" />
		<input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			<spring:message code="label.to" />
		<input type="text" name="stationName2" placeholder="Sknyliv" />&nbsp;&nbsp;&nbsp;
		<%-- Select Date Field --%>
		<spring:message code="label.date" />&nbsp;&nbsp;<input type="text"
			id="sDate" name="sDate" />&nbsp;&nbsp;&nbsp;
		<%-- Select Date Field END --%>
		<input class="button" type="submit" name="submit" value="<spring:message code="label.find" />" />
		<br />
		<br />
	</form:form>
	<div id="result">
		<%-- Results --%>
		<c:if test="${!empty TransportTravelList}">
			<hr />
			<table>
				<tr>
					<th>N</th>
					<th><a href="javascript:void(0);"
						onclick="showTransportPage('${param.stationName1}','${param.stationName2}', '${param.sDate}', ${pageNumber},${resultsPerPage}, '1')">
							<spring:message code="label.transportTravel.stationStop" />
					</a></th>
					<th><a href="javascript:void(0);"
						onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '2')">
							<spring:message code="label.transportTravel.number" />
					</a></th>
					<th><a href="javascript:void(0);"
						onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '3')">
							<spring:message code="label.transportTravel.depArr" />
					</a></th>
					<th><a href="javascript:void(0);"
						onclick="showTransportPage('${param.stationName1}','${param.stationName2}','${param.sDate}', ${pageNumber},${resultsPerPage}, '4')">
							<spring:message code="label.transportTravel.duration" />
					</a></th>
					<c:if test="${not empty param.sDate}">
						<th colspan="3"><spring:message code='label.tickets.purchase' /></th>
					</c:if>
				</tr>
				<c:forEach var="transport" items="${TransportTravelList}">
					<tr>
						<td id="generate"></td>
						<td>${transport.getLineName()}</td>
						<td>${transport.getTransport().getTransportCode()}</td>
						<td><spring:message code="label.transportTravel.dep" />
							${transport.getDepartureTime()}<br />
						<spring:message code="label.transportTravel.arr" />
							${transport.getArrivalTime()}</td>
						<td>${transport.getDuration()}</td>
						<c:if test="${not empty param.sDate}">
							<td><a
								href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/1">Class
									1</a></td>
							<td><a
								href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/2">Class
									2</a></td>
							<td><a
								href="/SoftServe_Java-105/reservationTicket/${transport.getTrip().getTripId()}/3">Class
									3</a></td>
							<%-- <td><a
								href="/SoftServe_Java-105/reservationTicket/${transport.getTripId()}"><input
									type="button"
									value="<spring:message code='label.tickets.purchase' />" /></a></td> --%>
						</c:if>
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
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&sDate=${param.sDate}&pageNumber=1&resultsPerPage=${resultsPerPage}">
								« </a></li>
						<li class="prev"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&sDate=${param.sDate}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
								href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&sDate=${param.sDate}&pageNumber=${i}&resultsPerPage=${resultsPerPage}">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&sDate=${param.sDate}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&sDate=${param.sDate}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
	<script>

		function showTransportPage(stationName1_, stationName2_,
					pageNumber_, resultsPerPage_, sDate_, orderBy_) {

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
										resultsPerPage : resultsPerPage_,
										sDate: sDate_,
										orderBy: orderBy_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

		function formDatePicker() {
			$.datepicker.setDefaults($.datepicker.regional['en']);
			$('#sDate').datepicker({ dateFormat: 'yy-mm-dd' }).val();
			$("#sDate").datepicker({
				defaultDate : "+0w",
				changeMonth : true,
				numberOfMonths : 1,
			});
		}
		
			$(window).load(
					function() {
						formDatePicker();
						showTransportPage("${param.stationName1}",
								"${param.stationName2}", "${pageNumber}",
								"${resultsPerPage}", "${param.sDate}", "${param.orderBy}");
								
			});
		</script>
</section>
