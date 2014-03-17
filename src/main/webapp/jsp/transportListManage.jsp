<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<section id="content">
	<script>
		var defaultDomElement = "div#pagingcontent";
		var defaultTargetPage = "transportListPageManage";
		var defaultGetData = {
			pageNumber : "${pageNumber}",
			resultsPerPage : "${resultsPerPage}",
			transportCode : "${container.getTransportCode()}",
			routeName : "${container.getRouteName()}",
			routesCode : "${container.getRoutesCode()}",
			seatClass1 : "${container.getSeatClass1()}",
			seatClass2 : "${container.getSeatClass2()}",
			seatClass3 : "${container.getSeatClass3()}",
			priceName : "${container.getPriceName()}",
			orderByCriteria : "${container.getOrderByCriteria()}",
			orderByDirection : "${container.getOrderByDirection()}"
		};
	</script>
	<p>
		<a href="/SoftServe_Java-105/formTransport.htm"> 
		<input id="Add" type="button" name="add" value="<spring:message code="label.transport.add"/>">
		</a>
	</p>
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
			<p>
				<br> <label for="routeName"> <spring:message
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
			<p>
				<br> <label for="routeCode"> <spring:message
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
	<p>
		<br>
	<h2 align="center">
		<spring:message code="label.navigation.transportManage" />	
	</h2>
	<c:if test="${not empty errormark}">
		<font color="red"><spring:message
				code="label.addtrips.errormessage" /> </font>
	</c:if>

	<div id="pagingcontent">
		<table style="align: center">
			<thead>
				<tr>
					<th>
						<div style="float: left">
							<spring:message code="label.transport.transportcode" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.transportCode&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.transportCode&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>

					</th>
					<th>
						<div style="float: left">
							<spring:message code="label.transport.starttime" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.startTime&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.startTime&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th>
						<div style="float: left">
							<spring:message code="label.routes.routecode" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.routes.routeCode&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.routes.routeCode&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th>
						<div style="float: left">
							<spring:message code="label.trips.routename" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.routes.routeName&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.routes.routeName&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.lines.linename" /></th>
					<th>
						<div style="float: left">
							<spring:message code="label.transport.seatclass1" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass1&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass1&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><div style="float: left">
							<spring:message code="label.transport.seatclass2" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass2&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass2&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><div style="float: left">
							<spring:message code="label.transport.seatclass3" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass3&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.seatclass3&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th>
						<div style="float: left">
							<spring:message code="label.transport.genprice" />
						</div>
						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.genPrice&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${
									resultsPerPage}&transportCode=${encoder.encode(
									container.getTransportCode())}&routeName=${encoder.encode(
									container.getRouteName())}&routesCode=${encoder.encode(
									container.getRoutesCode())}&seatClass1=${
									container.getSeatClass1()}&seatClass2=${
									container.getSeatClass2()}&seatClass3=${
									container.getSeatClass3()}&priceName=${encoder.encode(
									container.getPriceName())}&orderByCriteria=t.genPrice&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th></th><th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="transport" items="${transportsList}">
					<tr>
						<td align="center">${transport.getTransportCode()}</td>
						<td align="center">${transport.getStartTime()}</td>
						<td align="center">${transport.getRoutes().getRouteCode()}</td>
						<td align="center">${transport.getRoutes().getRouteName()}</td>
						<%-- <td align="center"><a
							href="getsLineId/${transport.getRoutes().getLineId().getLineId()}">${transport.getRoutes().getLineId().getLineName()}</a></td> --%>
						<td align="center">${transport.getSeatclass1()}</td>
						<td align="center">${transport.getSeatclass2()}</td>
						<td align="center">${transport.getSeatclass3()}</td>
						<td align="center">${transport.getGenPrice()}</td>
						<td align="center"><a href="editTransport/${transport.getTransportId()}"> 
							<input id="edit" type="button" name="edit" value="<spring:message code="label.edit"/>"></a>
						</td>
						<td align="center"><a href="removeTransport/${transport.getTransportId()}">
						<input id="delete" type="button" name="delete" onclick="return confirm_delete()"
								value="<spring:message code="label.delete"/>"></a>
						</td>
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

	</div>

	<script>
		$(window)
				.load(
						function() {
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
										if (prevVal !== curVal) {
											var searchData = {
												pageNumber : 1,
												resultsPerPage : "${resultsPerPage}",
												transportCode : $(
														"input#transportCode")
														.val(),
												routeName : $("input#routeName")
														.val(),
												routesCode : $(
														"input#routesCode")
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
												priceName : $(
														"input#priceName")
														.val(),
												orderByCriteria : "${container.getOrderByCriteria()}",
												orderByDirection : "${container.getOrderByDirection()}"
											};
											ajaxLoader(defaultDomElement,
													defaultTargetPage,
													searchData);
										}
									}, 3000);
						});
	</script>
	<script type="text/javascript">
	<!--
		function confirm_delete() {
			return confirm('Are you sure?');
		}
	//-->
	</script>
</section>
