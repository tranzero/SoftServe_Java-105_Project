<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
	<h2>
		<spring:message code="label.navigation.trips" />
	</h2>
	<div id="result">
		<table style="align: center">
			<tr>
				<th><spring:message code="label.transport.transportcode" /></th>
				<th><spring:message code="label.trips.remseatclass1" /></th>
				<th><spring:message code="label.trips.remseatclass2" /></th>
				<th><spring:message code="label.trips.remseatclass3" /></th>
				<th><spring:message code="label.trips.date" /></th>
				<th><spring:message code="label.transport.starttime" /></th>
			</tr>
			<c:forEach items="${tripsList}" var="trip">
				<tr>
					<td align="center">${trip.getTransport().getTransportCode()}</td>
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
		function showTripsPage(pageNumber_, resultsPerPage_) {

			$.ajax({
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

			showTripsPage("${pageNumber}", "${resultsPerPage}");

		});
		
	</script>
</section>