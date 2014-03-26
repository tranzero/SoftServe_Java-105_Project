<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table style="align: center">
	<thead>
		<tr>
			<th></th>
			<th>
				<div style="float: left">
					<spring:message code="label.transport.transportcode" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="transportcodeasc"><img
						alt="^" src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="transportcodedesc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
			<th>
				<div style="float: left">
					<spring:message code="label.transport.starttime" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="starttimeasc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="starttimedesc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
			<th>
				<div style="float: left">
					<spring:message code="label.routes.routecode" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="routecodeasc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="routecodedesc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
			<th>
				<div style="float: left">
					<spring:message code="label.trips.routename" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="routenameasc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="routenamedesc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
			<th>
				<div style="float: left">
					<spring:message code="label.transport.seatclass1" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="seatclass1asc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="seatclass1desc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
			<th><div style="float: left">
					<spring:message code="label.transport.seatclass2" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="seatclass2asc"><img alt="^"
						src="../resources/images/downarrow.png"></a> 
						<a href="javascript:void(0);" id="seatclass2desc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div></th>
			<th><div style="float: left">
					<spring:message code="label.transport.seatclass3" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="seatclass3asc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="seatclass3desc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div></th>
			<th>
				<div style="float: left">
					<spring:message code="label.transport.genprice" />
				</div>
				<div style="float: right">
					<a href="javascript:void(0);" id="genpriceasc"><img alt="^"
						src="../resources/images/downarrow.png"></a> <a
						href="javascript:void(0);" id="genpricedesc"><img alt="v"
						src="../resources/images/uparrow.png"></a>
				</div>
			</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${transportsList}" var="transport">
			<tr>

				<td><c:if test="${currentTrip.getTransport().getTransportId() == transport.getTransportId() }">
							<input type="radio" name="transportId"
								value="${transport.getTransportId()}" checked/>
								</c:if>
								<c:if test="${currentTrip.getTransport().getTransportId() != transport.getTransportId() }">
							<input type="radio" name="transportId"
								value="${transport.getTransportId()}"/>
								</c:if></td>
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

<p>
	<br>


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
		resultPerPage10Data.pageNumber = -1;
		$("a#resultsPerPage10").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							resultPerPage10Data);
				});

		resultPerPage20Data = clone(defaultGetData);
		resultPerPage20Data.resultsPerPage = 20;
		resultPerPage20Data.pageNumber = -1;
		$("a#resultsPerPage20").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							resultPerPage20Data);
				});

		resultPerPage50Data = clone(defaultGetData);
		resultPerPage50Data.resultsPerPage = 50;
		resultPerPage50Data.pageNumber = -1;
		$("a#resultsPerPage50").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							resultPerPage50Data);
				});

		transportcodeascData = clone(defaultGetData);
		transportcodeascData.orderByCriteria = "t.transportCode";
		transportcodeascData.pageNumber = -1;
		transportcodeascData.orderByDirection = "ASC";
		$("a#transportcodeasc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							transportcodeascData);
				});

		transportcodedescData = clone(defaultGetData);
		transportcodedescData.orderByCriteria = "t.transportCode";
		transportcodedescData.orderByDirection = "DESC";
		transportcodedescData.pageNumber = -1;
		$("a#transportcodedesc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							transportcodedescData);
				});

		routecodeascData = clone(defaultGetData);
		routecodeascData.orderByCriteria = "t.routes.routeCode";
		routecodeascData.pageNumber = -1;
		routecodeascData.orderByDirection = "ASC";
		$("a#routecodeasc").click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, routecodeascData);
		});

		routecodedescData = clone(defaultGetData);
		routecodedescData.orderByCriteria = "t.routes.routeCode";
		routecodedescData.orderByDirection = "DESC";
		routecodedescData.pageNumber = -1;
		$("a#routecodedesc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							routecodedescData);
				});

		routenameascData = clone(defaultGetData);
		routenameascData.orderByCriteria = "t.routes.routeName";
		routenameascData.pageNumber = -1;
		routenameascData.orderByDirection = "ASC";
		$("a#routenameasc").click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, routenameascData);
		});

		routenamedescData = clone(defaultGetData);
		routenamedescData.orderByCriteria = "t.routes.routeName";
		routenamedescData.orderByDirection = "DESC";
		routenamedescData.pageNumber = -1;
		$("a#routenamedesc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							routenamedescData);
				});

		seatclass1ascData = clone(defaultGetData);
		seatclass1ascData.orderByCriteria = "t.seatclass1";
		seatclass1ascData.pageNumber = -1;
		seatclass1ascData.orderByDirection = "ASC";
		$("a#seatclass1asc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass1ascData);
				});

		seatclass1descData = clone(defaultGetData);
		seatclass1descData.orderByCriteria = "t.seatclass1";
		seatclass1descData.orderByDirection = "DESC";
		seatclass1descData.pageNumber = -1;
		$("a#seatclass1desc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass1descData);
				});

		seatclass2ascData = clone(defaultGetData);
		seatclass2ascData.orderByCriteria = "t.seatclass2";
		seatclass2ascData.pageNumber = -1;
		seatclass2ascData.orderByDirection = "ASC";
		$("a#seatclass2asc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass2ascData);
				});

		seatclass2descData = clone(defaultGetData);
		seatclass2descData.orderByCriteria = "t.seatclass2";
		seatclass2descData.orderByDirection = "DESC";
		seatclass2descData.pageNumber = -1;
		$("a#seatclass2desc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass2descData);
				});

		seatclass3ascData = clone(defaultGetData);
		seatclass3ascData.orderByCriteria = "t.seatclass3";
		seatclass3ascData.pageNumber = -1;
		seatclass3ascData.orderByDirection = "ASC";
		$("a#seatclass3asc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass3ascData);
				});

		seatclass3descData = clone(defaultGetData);
		seatclass3descData.orderByCriteria = "t.seatclass3";
		seatclass3descData.orderByDirection = "DESC";
		seatclass3descData.pageNumber = -1;
		$("a#seatclass3desc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							seatclass3descData);
				});

		genpriceascData = clone(defaultGetData);
		genpriceascData.orderByCriteria = "t.genPrice";
		genpriceascData.pageNumber = -1;
		genpriceascData.orderByDirection = "ASC";
		$("a#genpriceasc").click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, genpriceascData);
		});

		genpricedescData = clone(defaultGetData);
		genpricedescData.orderByCriteria = "t.genPrice";
		genpricedescData.orderByDirection = "DESC";
		genpricedescData.pageNumber = -1;
		$("a#genpricedesc").click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, genpricedescData);
		});

		starttimeascData = clone(defaultGetData);
		starttimeascData.orderByCriteria = 't.startTime';
		starttimeascData.pageNumber = -1;
		starttimeascData.orderByDirection = "ASC";
		$("a#starttimeasc").click(function() {
			ajaxLoader(defaultDomElement, defaultTargetPage, starttimeascData);
		});

		starttimedescData = clone(defaultGetData);
		starttimedescData.orderByCriteria = 't.startTime';
		starttimedescData.orderByDirection = "DESC";
		starttimedescData.pageNumber = -1;
		$("a#starttimedesc").click(
				function() {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							starttimedescData);
				});

		defaultGetData.pageNumber = ${pageNumber};
		
		window.history
				.pushState(defaultGetData, document.title, location.protocol
						+ '//' + location.host + location.pathname + '?'
						+ serialize(defaultGetData) + '&lang=${language}');
		window.history.pathname = document.location.href;
	</script>