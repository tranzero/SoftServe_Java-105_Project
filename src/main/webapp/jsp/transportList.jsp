<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
	<script>
		var defaultDomElement = "div#result";
		var defaultTargetPage = "transportListPage";
		var defaultGetData = {
			pageNumber : "${pageNumber}",
			resultsPerPage : "${resultsPerPage}",
			transportCode : "${container.getTransportCode()}",
			routeCode : "${container.getRouteCode()}",
			lineName : "${container.getLineName()}",
			seatClass1 : "${container.getSeatClass1()}",
			seatClass2 : "${container.getSeatClass2()}",
			seatClass3 : "${container.getSeatClass3()}",
			genPrice : "${container.getGenPrice()}",
			minTime : "${container.getMinTimeString()}",
			maxTime : "${container.getMaxTimeString()}",
			orderByParam : "${container.getOrderByParam()}",
			orderByDirection : "${container.getOrderByDirection()}"
		};
	</script>
	<h2>
		<spring:message code="label.transport.transportcode" />
	</h2>
	<form id="searchForm" method="get">
		<p>
		<h3>
			<spring:message code="label.trips.setsearchparameters" />
			:
		</h3>
		<p>
				<spring:message code="label.transport.transportcode" />
			:
			<c:if test="${isTransportCode}">
				<input class="autosearch" type="text" id="transportCode"
					name="transportCode">
			</c:if>
			<c:if test="${!isTransportCode}">
				<input class="autosearch" type="text" id="transportCode"
					name="transportCode" value="${container.getTransportCode()}">
			</c:if>
				<spring:message code="label.routes.routecode" />
			:
			<c:if test="${isRouteCode}">
				<input class="autosearch" type="text" id="routeCode"
					name="routeCode">
			</c:if>
			<c:if test="${!isRouteCode}">
				<input class="autosearch" type="text" id="routeCode"
					name="routeCode" value="${container.getRouteCode()}">
			</c:if>
		<p>
				<spring:message code="label.transport.seatclass1" />
			:
		<p>
			<label for="seatClass1">1:</label>
			<c:if test="${isClass1}">
				<input class="autosearch" type="number" id="seatClass1"
					name="seatClass1">
			</c:if>
			<c:if test="${!isClass1}">
				<input class="autosearch" type="number" id="seatClass1"
					name="seatClass1" value="${container.getSeatClass1()}">
			</c:if>
			<label for="seatClass2">2:</label>
			<c:if test="${isClass2}">
				<input class="autosearch" type="number" id="seatClass2"
					name="seatClass2">
			</c:if>
			<c:if test="${!isClass2}">
				<input class="autosearch" type="number" id="seatClass2"
					name="seatClass2" value="${container.getSeatClass2()}">
			</c:if>
			<label for="seatClass3">3:</label>
			<c:if test="${isClass3}">
				<input class="autosearch" type="number" id="seatClass3"
					name="seatClass3">
			</c:if>
			<c:if test="${!isClass3}">
				<input class="autosearch" type="number" id="seatClass3"
					name="seatClass3" value="${container.getSeatClass3()}">
			</c:if>
			<p>
				<spring:message code="label.transport.genprice" />
			:
			<c:if test="${isGenPrice}">
				<input class="autosearch" type="text" id="genPrice"	name="genPrice">
			</c:if>
			<c:if test="${!isGenPrice}">
				<input class="autosearch" type="text" id="genPrice"
					name="genPrice" value="${container.getTransportCode()}">
			</c:if>
		<p>
			<spring:message code="label.trips.daterange" />
			:
		<p>
			<label for="minTime"><spring:message code="label.addtrips.from" /></label>
			<c:if test="${isMinTime}">
				<input class="autosearch" type="text" id="from" name="minTime">
			</c:if>
			<c:if test="${!isMinTime}">
				<input class="autosearch" type="text" id="from" name="minTime"
					value="${container.getMinTimeString()}">
			</c:if>


			<label for="maxTime"><spring:message code="label.addtrips.to" /></label>
			<c:if test="${isMaxTime}">
				<input class="autosearch" type="text" id="to" name="maxTime">
			</c:if>
			<c:if test="${!isMaxTime}">
				<input class="autosearch" type="text" id="to" name="maxTime"
					value="${container.getMaxTimeString()}">
			</c:if>
		<div style="float: right">
			<input type="submit"
				value="<spring:message code="label.trips.tripsearch"/>">
		</div>
		<br>
	</form>
	<p>
		<br>
		<div id="result">
		<table style="align: center">
			<tr>
				<th><div style="float: left">
						<spring:message code="label.transport.transportcode" />
					</div>
					<div style="float: right">
						<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.transportCode&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> 
						<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.transportCode&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div>
		</th>
		<th><div style="float: left">
				<spring:message code="label.transport.starttime" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startTime&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a> 
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startTime&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.routename" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.routes.routeCode&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a> 
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.routes.routeCode&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass1" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass1&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a> 
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass1&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass2" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass2&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a>
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass2&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.remseatclass3" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass3&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a>
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.seatClass3&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
			<th><div style="float: left">
				<spring:message code="label.transport.genprice" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.genPrice&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a>
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.genPrice&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.trips.date" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startDate&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a>
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startDate&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th><div style="float: left">
				<spring:message code="label.transport.starttime" />
			</div>
			<div style="float: right">
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startTime&orderByDirection=ASC">
					<img alt="^" src="resources/images/downarrow.png">
				</a>
				<a href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())
						}&orderByParam=t.startTime&orderByDirection=DESC">
					<img alt="v" src="resources/images/uparrow.png">
				</a>
			</div></th>
		<th></th>
		</tr>
		<c:forEach var="transport" items="${transportsList}" >
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
				<td><a href=" transportResponses/${transport.getTransportId()}"><spring:message
							code="label.navigation.checkresponses" />...</a></td>
			</tr>
		</c:forEach></table>

		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							« </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
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
							href="?pageNumber=${i}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
								${i} </a></li>
					</c:if>
					<c:if test="${pageNumber==i}">
						<li class="disabled"><a href="javascript:void(0);"> ${i}
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNumber<maxPages}">
					<li class="next"><a
						href="?pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
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
			<p>
			<p>
				<br>
				<spring:message code="label.trips.resultsperpage" />
				:
			<ul class="bootpag">
				<c:if test="${resultsPerPage!=10}">
					<li><a
						href="?resultsPerPage=10&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							10</a></li>
				</c:if>
				<c:if test="${resultsPerPage==10}">
					<li class="disabled"><a href="javascript:void(0);">10</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=20}">
					<li><a
						href="?resultsPerPage=20&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							20</a></li>
				</c:if>
				<c:if test="${resultsPerPage==20}">
					<li class="disabled"><a href="javascript:void(0);">20</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=50}">
					<li><a
						href="?resultsPerPage=50&transportCode=${encoder.encode(
						container.getTransportCode())}&startTime=${
						container.getStartTime()}&routeCode=${encoder.encode(
						container.getRouteCode())}&lineName=${
						container.getLineName()}&seatClass1=${
						container.getSeatClass1()}&seatClass2=${
						container.getSeatClass2()}&seatClass3=${
						container.getSeatClass3()}&genPrice=${
						container.getGenPrice()}&minTime=${encoder.encode(
						container.getMinTimeString())}&maxTime=${encoder.encode(
						container.getMaxTimeString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							50</a></li>
				</c:if>
				<c:if test="${resultsPerPage==50}">
					<li class="disabled"><a href="javascript:void(0);">50</a></li>
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
					$("#to").datepicker("option", "minTime", selectedDate);
				}
			});
			$("#to").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 3,
				onClose : function(selectedDate) {
					$("#from").datepicker("option", "maxTime", selectedDate);
				}
			});

		}

		$(window)
				.load(
						function() {
							formDatePicker();
							$("input#seatClass1").ForceNumericOnly();
							$("input#seatClass2").ForceNumericOnly();
							$("input#seatClass3").ForceNumericOnly();
							setInterval(
									function() {
										var curVal;
										var prevVal;
										curVal = $(".autosearch").serialize();
										prevVal = $(".autosearch").data(
												"prevVal")
												|| null;
										$(".autosearch")
												.data("prevVal", curVal);
										if ((prevVal != null)
												&& (prevVal !== curVal)) {
											var searchData = {
												pageNumber : 1,
												resultsPerPage : "${resultsPerPage}",
												transportCode : $(
														"input#transportCode")
														.val(),
												startTime : $("input#startTime")
														.val(),
												routeCode : $("input#routeCode")
														.val(),
												lineName : $("input#lineName")
														.val(),
												seatClass1 : $(
														"input#seatClass1")
														.val(),
												seatClass2 : $(
														"input#seatClass2")
														.val(),
												seatClass3 : $(
														"input#seatClass3")
														.val(),
												genPrice : $(
														"input#genPrice")
														.val(),
												minTime : $("input#from").val(),
												maxTime : $("input#to").val(),
												orderByParam : "${container.getOrderByParam()}",
												orderByDirection : "${container.getOrderByDirection()}"
											};
											ajaxLoader(defaultDomElement,
													defaultTargetPage,
													searchData);
										}
									}, 3000);
						});
	</script>
</section>