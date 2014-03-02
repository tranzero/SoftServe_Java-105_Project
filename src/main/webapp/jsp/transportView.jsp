<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- JS for table sorting -->
<link rel="shortcut icon" href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">
<link rel="icon" href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">
<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.tablesorter.min.js"></script>

<!-- JS for table searching -->
<script src="resources/js/jquery.searcher.js"></script>

<style>
<!--
.transportTableArrows thead tr th span {
	padding-right: 20px;
	background-repeat: no-repeat;
	background-position: 100% 100%;
}

.transportTableArrows thead tr th.headerSortUp,.transportTableArrows thead tr th.headerSortDown
	{
	background: #acc8dd;
}

.transportTableArrows thead tr th.headerSortUp span {
	background-image: url('resources/images/up-arrow.png');
}

.transportTableArrows thead tr th.headerSortDown span {
	background-image: url('resources/images/down-arrow.png');
}
-->
</style>

<section id="content">
	<p>
		<label for="tableSearchInput"><spring:message code="label.transport.tableSearchInput" /></label>
		<input id="tableSearchInput" type="text" />
	</p>
	<h2 align="center">
		<spring:message code="label.navigation.transport" />
	</h2>
	<div id="pagingcontent">
		<table id="transportTable" class="transportTableArrows">
			<thead>
				<tr>
					<th><span><spring:message code="label.transport.transportcode" /></span></th>
					<th><span><spring:message code="label.transport.starttime" /></span></th>
					<th><span><spring:message code="label.routes.routecode" /></span></th>
					<th><span><spring:message code="label.lines.linename" /></span></th>
					<th><span><spring:message code="label.transport.seatclass1" /></span></th>
					<th><span><spring:message code="label.transport.seatclass2" /></span></th>
					<th><span><spring:message code="label.transport.seatclass3" /></span></th>
					<th><span><spring:message code="label.transport.genprice" /></span></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transportsList}" var="transport">
					<tr>
						<td align="center">${transport.getTransportCode()}</td>
						<td align="center">${transport.getStartTime()}</td>
						<td align="center">${transport.getRoutes().getRouteCode()}</td>
						<td align="center"><a
							href="getsLineId/${transport.getRoutes().getLineId().getLineId()}">${transport.getRoutes().getLineId().getLineName()}</a></td>
						<td align="center">${transport.getSeatclass1()}</td>
						<td align="center">${transport.getSeatclass2()}</td>
						<td align="center">${transport.getSeatclass3()}</td>
						<td align="center">${transport.getGenPrice()}</td>
					</tr>
				</c:forEach>
			</tbody>
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
	<!--
		function showTransportPage(pageNumber_, resultsPerPage_) {
			$.ajax({
						async : true,
						beforeSend : function() {
							$("div#result").html(
											'_$tag_______________________________________________________');
									},
						type : "GET",
						url : "transportpageView",
						data : {
							pageNumber : pageNumber_,
							resultsPerPage : resultsPerPage_
						}
					}).done(function(msg) {
				$("div#result").html(msg);
			});
		}

		$(window).load(function() {
			showTransportPage("${pageNumber}", "${resultsPerPage}");
		});
		//-->
	</script>
	<!-- JS for table sorting -->
	<script type="text/javascript">
	<!--
		$(function() {
			$('#transportTable').tablesorter();
		});
	//-->
	</script>
	<!-- JS for table searching -->
	<script type="text/javascript">
	<!--
		$("#transportTable").searcher({
			inputSelector : "#tableSearchInput"
		// itemSelector (tbody > tr) and textSelector (td) already have proper default values
		});
	//-->
	</script>
</section>
