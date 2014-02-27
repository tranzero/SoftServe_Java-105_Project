<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table style="align: center">
	<tr>
				<th><div style="float: left">
				<spring:message code="label.transport.transportcode" />
			</div>
			<div style="float: right">
				<a href="#" id="transportcodeasc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="transportcodedesc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.routename" />
			</div>
			<div style="float: right">
				<a href="#" id="routenameasc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="routenamedesc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass1" />
			</div>
			<div style="float: right">
				<a href="#" id="class1asc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="class1desc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass2" />
			</div>
			<div style="float: right">
				<a href="#" id="class2asc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="class2desc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass3" />
			</div>
			<div style="float: right">
				<a href="#" id="class3asc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="class3desc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.date" />
			</div>
			<div style="float: right">
				<a href="#" id="dateasc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="datedesc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.transport.starttime" />
			</div>
			<div style="float: right">
				<a href="#" id="timeasc"><img alt="^" src="resources/images/downarrow.png"></a> 
				<a href="#" id="timedesc"><img alt="v" src="resources/images/uparrow.png"></a>
			</div></th>
				<th></th>
				<th></th>
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
			<td align="center"><a> <input id="edit" type="button"
					name="edit" value="<spring:message code="label.edit"/>">
			</a></td>
			<td align="center"><a> <input id="delete" type="button"
					name="delete" value="<spring:message code="label.delete"/>">
			</a>
		</tr>
	</c:forEach>
</table>


<div class="pagination">
	<ul class="bootpag">
		<c:if test="${pageNumber>1}">
			<li class="prev"><a href="javascript:void(0);"
				id="firstpage"> « </a></li>
			<li class="prev"><a href="javascript:void(0);"
				id="prevpage"> <spring:message
						code="label.prev" />
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
				<li><a href="javascript:void(0);"
					id="page${i}"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				id="nextpage"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				id="lastpage"> » </a></li>
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
			<li ><a href="javascript:void(0);" id="resultsPerPage50">50</a></li>
		</c:if>
		<c:if test="${resultsPerPage==50}">
			<li class="disabled"><a href="javascript:void(0);">50</a></li>
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
	
	resultPerPage10Data  = clone(defaultGetData);
	resultPerPage10Data.resultsPerPage = 10;
	resultPerPage10Data.pageNumber = 1;
	$("a#resultsPerPage10").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage10Data);
	});
	
	resultPerPage20Data  = clone(defaultGetData);
	resultPerPage20Data.resultsPerPage = 20;
	resultPerPage20Data.pageNumber = 1;
	$("a#resultsPerPage20").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage20Data);
	});

	resultPerPage50Data  = clone(defaultGetData);
	resultPerPage50Data.resultsPerPage = 50;
	resultPerPage50Data.pageNumber = 1;
	$("a#resultsPerPage50").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage50Data);
	});
	
	
	transportcodeascData = clone(defaultGetData);
	transportcodeascData.orderByParam = "tr.transport.transportCode";
	transportcodeascData.pageNumber = 1;
	transportcodeascData.orderByDirection = "ASC";
	$("a#transportcodeasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, transportcodeascData);
	});
	
	transportcodedescData = clone(defaultGetData);
	transportcodedescData.orderByParam = "tr.transport.transportCode";
	transportcodedescData.orderByDirection = "DESC";
	transportcodedescData.pageNumber = 1;
	$("a#transportcodedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, transportcodedescData);
	});
	
	routenameascData = clone(defaultGetData);
	routenameascData.orderByParam = "tr.transport.routes.routeName";
	routenameascData.orderByDirection = "ASC";
	routenameascData.pageNumber = 1;
	$("a#routenameasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, routenameascData);
	});
	
	routenamedescData = clone(defaultGetData);
	routenamedescData.orderByParam = "tr.transport.routes.routeName";
	routenamedescData.orderByDirection = "DESC";
	routenamedescData.pageNumber = 1;
	$("a#routenamedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, routenamedescData);
	});
	
	class1ascData = clone(defaultGetData);
	class1ascData.orderByParam = "tr.remSeatClass1";
	class1ascData.orderByDirection = "ASC";
	class1ascData.pageNumber = 1;
	$("a#class1asc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class1ascData);
	});
	
	class1descData = clone(defaultGetData);
	class1descData.orderByParam = "tr.remSeatClass1";
	class1descData.orderByDirection = "DESC";
	class1descData.pageNumber = 1;
	$("a#class1desc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class1descData);
	});

	class2ascData = clone(defaultGetData);
	class2ascData.orderByParam = "tr.remSeatClass2";
	class2ascData.orderByDirection = "ASC";
	class2ascData.pageNumber = 1;
	$("a#class2asc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class2ascData);
	});
	
	class2descData = clone(defaultGetData);
	class2descData.orderByParam = "tr.remSeatClass2";
	class2descData.orderByDirection = "DESC";
	class2descData.pageNumber = 1;
	$("a#class2desc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class2descData);
	});

	class3ascData = clone(defaultGetData);
	class3ascData.orderByParam = "tr.remSeatClass3";
	class3ascData.orderByDirection = "ASC";
	class3ascData.pageNumber = 1;
	$("a#class3asc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class3ascData);
	});
	
	class3descData = clone(defaultGetData);
	class3descData.orderByParam = "tr.remSeatClass3";
	class3descData.orderByDirection = "DESC";
	class3descData.pageNumber = 1;
	$("a#class3desc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, class3descData);
	});

	dateascData = clone(defaultGetData);
	dateascData.orderByParam = "tr.startDate";
	dateascData.orderByDirection = "ASC";
	dateascData.pageNumber = 1;
	$("a#dateasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, dateascData);
	});
	
	datedescData = clone(defaultGetData);
	datedescData.orderByParam = "tr.startDate";
	datedescData.orderByDirection = "DESC";
	datedescData.pageNumber = 1;
	$("a#datedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, datedescData);
	});

	timeascData = clone(defaultGetData);
	timeascData.orderByParam = "tr.transport.startTime";
	timeascData.orderByDirection = "ASC";
	timeascData.pageNumber = 1;
	$("a#timeasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, timeascData);
	});
	
	timedescData = clone(defaultGetData);
	timedescData.orderByParam = "tr.transport.startTime";
	timedescData.orderByDirection = "DESC";
	timedescData.pageNumber = 1;
	$("a#timedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, timedescData);
	});
	
	
</script>