<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
	<h2>
		<spring:message code="label.navigation.trips" />
	</h2>
	<form id="searchForm">
		<p>
		<h3><spring:message code="label.trips.setsearchparameters" />:</h3>
		<p>
			<spring:message code="label.transport.transportcode" />
			:
			<c:if test="${isTransportCode}">
				<input type="text" id="transportCode" name="transportCode">
			</c:if>
			<c:if test="${!isTransportCode}">
				<input type="text" id="transportCode" name="transportCode"
					value="${container.getTransportCode()}">
			</c:if>
			<spring:message code="label.trips.routename" />
			:
			<c:if test="${isRouteName}">
				<input type="text" id="routeName" name="routeName">
			</c:if>
			<c:if test="${!isRouteName}">
				<input type="text" id="routeName" name="routeName"
					value="${container.getRouteName()}">
			</c:if>
		<p><spring:message code="label.trips.minimalremainingseatsbyclasses" />:
		<p>
			<label for="remSeatClass1">1:</label>
			<c:if test="${isClass1}">
				<input type="text" id="remSeatClass1" name="remSeatClass1">
			</c:if>
			<c:if test="${!isClass1}">
				<input type="text" id="remSeatClass1" name="remSeatClass1"
					value="${container.getRemSeatClass1}">
			</c:if>
			<label for="remSeatClass2">2:</label>
			<c:if test="${isClass2}">
				<input type="text" id="remSeatClass2" name="remSeatClass2">
			</c:if>
			<c:if test="${!isClass2}">
				<input type="text" id="remSeatClass2" name="remSeatClass2"
					value="${container.getRemSeatClass2}">
			</c:if>
			<label for="remSeatClass3">3:</label>
			<c:if test="${isClass3}">
				<input type="text" id="remSeatClass3" name="remSeatClass3">
			</c:if>
			<c:if test="${!isClass3}">
				<input type="text" id="remSeatClass3" name="remSeatClass3"
					value="${container.getRemSeatClass3}">
			</c:if>
		<p><spring:message code="label.trips.daterange" />:
		<p>
			<label for="minDate"><spring:message
					code="label.addtrips.from" /></label>
			<c:if test="${isMinDate}">
				<input type="text" id="from" name="minDate">
			</c:if>
			<c:if test="${!isMinDate}">
				<input type="text" id="from" name="minDate"
					value="${container.getMinDateString()}">
			</c:if>


			<label for="maxDate"><spring:message code="label.addtrips.to" /></label>
			<c:if test="${isMaxDate}">
				<input type="text" id="to" name="maxDate">
			</c:if>
			<c:if test="${!isMaxDate}">
				<input type="text" id="to" name="maxDate"
					value="${container.getMaxDateString()}">
			</c:if>
	</form>
	<div id="result">
		<table style="align: center">
			<tr>
				<th><spring:message code="label.transport.transportcode" /></th>
				<th><spring:message code="label.trips.routename" /></th>
				<th><spring:message code="label.trips.remseatclass1" /></th>
				<th><spring:message code="label.trips.remseatclass2" /></th>
				<th><spring:message code="label.trips.remseatclass3" /></th>
				<th><spring:message code="label.trips.date" /></th>
				<th><spring:message code="label.transport.starttime" /></th>
			</tr>
			<c:forEach items="${tripsList}" var="trip">
				<tr>
					<td align="center">${trip.getTransport().getTransportCode()}</td>
					<td align="center">${trip.getTransport().getRoutes().getRouteName()}</td>
					<td align="center">${trip.getRemSeatClass1()}</td>
					<td align="center">${trip.getRemSeatClass2()}</td>
					<td align="center">${trip.getRemSeatClass3()}</td>
					<td align="center">${dateFormat.format(trip.getStartDate())}</td>
					<td align="center">${trip.getTransport().getStartTime()}</td>
				</tr>
			</c:forEach>
		</table>

		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}"> « </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
						<li><a
							href="?pageNumber=${i}&resultsPerPage=${resultsPerPage}">
								${i} </a></li>
					</c:if>
					<c:if test="${pageNumber==i}">
						<li class="disabled"><a href="javascript:void(0);"> ${i}
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNumber<maxPages}">
					<li class="next"><a
						href="?pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
	</div>


	<script>
		function formDatePicker() {
			$.datepicker.setDefaults($.datepicker.regional['${language}']);
			$("#from").datepicker({
				defaultDate : "+0w",
				changeMonth : true,
				numberOfMonths : 3,
				onClose : function(selectedDate) {
					$("#to").datepicker("option", "minDate", selectedDate);
				}
			});
			$("#to").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 3,
				onClose : function(selectedDate) {
					$("#from").datepicker("option", "maxDate", selectedDate);
				}
			});

		}
		function showTripsPage(pageNumber_, resultsPerPage_) {

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
								url : "tripspage",
								data : {
									pageNumber : pageNumber_,
									resultsPerPage : resultsPerPage_
								}
							}).done(function(msg) {
						$("div#result").html(msg);
					});

		}

		$(window).load(function() {
			formDatePicker();

			// 			showTripsPage("${pageNumber}", "${resultsPerPage}");

		});
	</script>
</section>