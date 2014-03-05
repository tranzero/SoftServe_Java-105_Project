<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<table style="align: center">
		<thead>
			<tr>
				<th></th>
				<th><spring:message code="label.transport.transportcode" /></th>
				<th><spring:message code="label.transport.starttime" /></th>
				<th><spring:message code="label.routes.routecode" /></th>
				<th><spring:message code="label.trips.routename" /></th>
				<th><spring:message code="label.transport.seatclass1" /></th>
				<th><spring:message code="label.transport.seatclass2" /></th>
				<th><spring:message code="label.transport.seatclass3" /></th>
				<th><spring:message code="label.transport.genprice" /></th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${transportsList}" var="transport">
				<tr>

					<td><input type="radio" name="transportid"
						value="${transport.getTransportId()}" /></td>
					<td align="center">${transport.getTransportCode()}</td>
					<td align="center">${transport.getStartTime()}</td>
					<td align="center">${transport.getRoutes().getRouteCode()}</td>
					<td align="center">${transport.getRoutes().getRouteName()}</td>
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
			<li class="prev"><a href="javascript:void(0);" id="firstpage">
					« </a></li>
			<li class="prev"><a href="javascript:void(0);" id="prevpage">
					<spring:message code="label.prev" />
			</a></li>
		</c:if>
		<c:if test="${pageNumber==1}">
			<li class="prev disabled"><a href="javascript:void(0);"> « </a></li>
			<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
						code="label.prev" />
			</a></li>
		</c:if>
		<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
			varStatus="status">
			<c:if test="${pageNumber!=i}">
				<li><a href="javascript:void(0);" id="page${i}"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);" id="nextpage">
					<spring:message code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);" id="lastpage">
					» </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>

	<p>
	<p>
		<br>
		<spring:message code="label.trips.resultsperpage" />
		:
	<ul class="bootpag">
		<c:if test="${resultsPerPage!=10}">
			<li><a href="javascript:void(0);" id="resultsPerPage10">10</a></li>
		</c:if>
		<c:if test="${resultsPerPage==10}">
			<li class="disabled"><a href="javascript:void(0);">10</a></li>
		</c:if>
		<c:if test="${resultsPerPage!=20}">
			<li><a href="javascript:void(0);" id="resultsPerPage20">20</a></li>
		</c:if>
		<c:if test="${resultsPerPage==20}">
			<li class="disabled"><a href="javascript:void(0);">20</a></li>
		</c:if>
		<c:if test="${resultsPerPage!=50}">
			<li><a href="javascript:void(0);" id="resultsPerPage50">50</a></li>
		</c:if>
		<c:if test="${resultsPerPage==50}">
			<li class="disabled"><a href="javascript:void(0);">50</a></li>
		</c:if>
	</ul>

</div>

<p><br>


<script>
	firstPageData = clone(defaultGetData);
	firstPageData.pageNumber = 1;

	$("a#firstpage").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, firstPageData);
	});

	prevPageData = clone(defaultGetData);
	prevPageData.pageNumber = '${pageNumber-1}';

	$("a#prevpage").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, prevPageData);
	});

	var pageElement = {};
	for (var temp = '${firstPage}' * 1; temp <= '${lastPage}' * 1; temp = temp + 1) {
		pageElement[temp] = clone(defaultGetData);
		pageElement[temp].pageNumber = temp;
	}

	$.each(pageElement, function(index, value) {
		$("a#page" + index).click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, value);
		});
	});

	nextPageData = clone(defaultGetData);
	nextPageData.pageNumber = '${pageNumber+1}';
	$("a#nextpage").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, nextPageData);
	});

	lastPageData = clone(defaultGetData);
	lastPageData.pageNumber = '${maxPages}';
	$("a#lastpage").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, lastPageData);
	});

	resultPerPage10Data = clone(defaultGetData);
	resultPerPage10Data.resultsPerPage = 10;
	resultPerPage10Data.pageNumber = 1;
	$("a#resultsPerPage10").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage10Data);
	});

	resultPerPage20Data = clone(defaultGetData);
	resultPerPage20Data.resultsPerPage = 20;
	resultPerPage20Data.pageNumber = 1;
	$("a#resultsPerPage20").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage20Data);
	});

	resultPerPage50Data = clone(defaultGetData);
	resultPerPage50Data.resultsPerPage = 50;
	resultPerPage50Data.pageNumber = 1;
	$("a#resultsPerPage50").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage50Data);
	});

</script>