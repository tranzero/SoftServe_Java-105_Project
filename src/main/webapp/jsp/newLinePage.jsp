<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="res_per_page" class="pagination half_width">
	<span id="res_per_page_label"><spring:message
			code="label.trips.resultsperpage" /> :</span>
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
<form action="confirmcreating" method="post">
	<spring:message code="label.lines.linename" />
	: <input type="text" name="newLineName"><br>

	<table class='table'>
		<thead>
			<tr>
				<th><spring:message code="label.stations.stationcode" />
					<div style="float: right">
						<a id="stationcodeasc" href="javascript:void(0);"> <img
							alt="^" src="resources/images/downarrow.png">
						</a> <a id="stationcodedesc" href="javascript:void(0);"> <img
							alt="^" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><spring:message code="label.stations.stationname" />
					<div style="float: right">
						<a id="stationnameasc" href="javascript:void(0);"> <img
							alt="^" src="resources/images/downarrow.png">
						</a> <a id="stationnamedesc" href="javascript:void(0);"> <img
							alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><spring:message code="label.lines.addstation" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="station" items="${stationsList}">
				<tr>
					<td align="center">${station.getStationCode()}</td>
					<td align="center">${station.getStationName()}</td>
					<td align="center"><input type="checkbox" name="stationsCheck"
						value="${station.getStationId()}"></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="submit"
		value="<spring:message
			code="label.lines.apply" />">
</form>

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
</div>

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

	stationcodeascData = clone(defaultGetData);
	stationcodeascData.orderByParam = "s.stationCode";
	stationcodeascData.orderByDirection = "ASC";
	stationcodeascData.pageNumber = 1;
	$("a#stationcodeasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, stationcodeascData);
	});

	stationcodedescData = clone(defaultGetData);
	stationcodedescData.orderByParam = "s.stationCode";
	stationcodedescData.orderByDirection = "DESC";
	stationcodedescData.pageNumber = 1;
	$("a#stationcodedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, stationcodedescData);
	});

	stationnameascData = clone(defaultGetData);
	stationnameascData.orderByParam = "s.stationName";
	stationnameascData.orderByDirection = "ASC";
	stationnameascData.pageNumber = 1;
	$("a#stationnameasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, stationnameascData);
	});

	stationnamedescData = clone(defaultGetData);
	stationnamedescData.orderByParam = "s.stationName";
	stationnamedescData.orderByDirection = "DESC";
	stationnamedescData.pageNumber = 1;
	$("a#stationnamedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, stationnamedescData);
	});

	window.history.pushState(defaultGetData, document.title, location.protocol
			+ '//' + location.host + location.pathname + '?'
			+ serialize(defaultGetData) + '&lang=${language}');
	window.history.pathname = document.location.href;
</script>