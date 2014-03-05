<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<section id="content">

	<form method="get">
		<div style="border: solid 1px lightgrey;">
			<h2 align="center">
				<spring:message code="label.transport.tableSearchInput" />
			</h2>
			<p>
				<br> <label for="transportCode"> <spring:message
						code="label.transport.transportcode" />:
				</label>
				<c:if test="${isTransportCode}">
					<input type="text" name="transportCode" id="transportCode"
						class="autosearch">
				</c:if>
				<c:if test="${!isTransportCode}">
					<input type="text" name="transportCode" id="transportCode"
						class="autosearch" value="${container.getTransportCode()}">
				</c:if>
				<label for="routeName"> <spring:message
						code="label.routes.routename" />:
				</label>
				<c:if test="${isRouteName}">
					<input type="text" name="routeName" id="routeName"
						class="autosearch">
				</c:if>
				<c:if test="${!isRouteName}">
					<input type="text" name="routeName" id="routeName"
						class="autosearch" value="${container.getRouteName()}">
				</c:if>
				<label for="routeCode"> <spring:message
						code="label.routes.routecode" />:
				</label>
				<c:if test="${isRoutesCode}">
					<input type="text" name="routesCode" id="routesCode"
						class="autosearch">
				</c:if>
				<c:if test="${!isRoutesCode}">
					<input type="text" name="routesCode" id="routesCode"
						class="autosearch" value="${container.getRoutesCode()}">
				</c:if>
			<p>
				<br>
				<spring:message code="label.addtrips.minimalseatsbyclasses" />
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
				<br> <label for="priceName"><spring:message
						code="label.addtrips.maximalprice" />:</label>
				<c:if test="${isPrice}">
					<input class="autosearch" type="number" id="priceName"
						name="priceName">
				</c:if>
				<c:if test="${!isPrice}">
					<input class="autosearch" type="number" id="priceName"
						name="priceName" value="${container.getPriceName()}">
				</c:if>
			<div style="float: right">
				<input type="submit"
					value="<spring:message code="label.addtrips.tripsearch"/>">
			</div>
		</div>

	</form>
	<h2 align="center">
		<spring:message code="label.addtrips.addtrips" />
	</h2>
	<c:if test="${not empty errormark}">
		<font color="red"><spring:message
				code="label.addtrips.errormessage" /> </font>
	</c:if>

	<form action="addNewTrips" name="trips" method="post">
		<h3>
			<spring:message code="label.addtrips.choosedateinterval" />
		</h3>
		<p>
			<label for="from"><spring:message code="label.addtrips.from" /></label>
			<input type="text" id="from" name="from"> <label for="to"><spring:message
					code="label.addtrips.to" /></label> <input type="text" id="to" name="to">
		<h3>
			<spring:message code="label.addtrips.choosetransport" />
		</h3>
		<div id="pagingcontent">
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
						<li class="prev"><a
							href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
								« </a></li>
						<li class="prev"><a
							href="?pageNumber=${pageNumber-1}&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
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
								href="?pageNumber=${i}&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
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
							href="?pageNumber=${pageNumber+1}&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?pageNumber=${maxPages}&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
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
			<p>
			<p>
				<br>
				<spring:message code="label.trips.resultsperpage" />
				:
			<ul class="bootpag">
				<c:if test="${resultsPerPage!=10}">
					<li><a
						href="?resultsPerPage=10&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
							10</a></li>
				</c:if>
				<c:if test="${resultsPerPage==10}">
					<li class="disabled"><a href="javascript:void(0);">10</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=20}">
					<li><a
						href="?&resultsPerPage=20&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
							20</a></li>
				</c:if>
				<c:if test="${resultsPerPage==20}">
					<li class="disabled"><a href="javascript:void(0);">20</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=50}">
					<li><a
						href="&resultsPerPage=50&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=${encoder.encode(
									container.getOrderByCriteria())}&orderByDirection=${encoder.encode(
									container.getOrderByDirection())}">
							50</a></li>
				</c:if>
				<c:if test="${resultsPerPage==50}">
					<li class="disabled"><a href="javascript:void(0);">50</a></li>
				</c:if>
			</ul>
			

		</div>
		<input type="submit"
			value="<spring:message code="label.navigation.addtrips"/>">
	</form>
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

		$(window).load(function() {
			formDatePicker();
		});
	</script>
</section>
