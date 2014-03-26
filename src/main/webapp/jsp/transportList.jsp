<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<section id="content">
	<script>
		var defaultDomElement = "div#pagingcontent";
		var defaultTargetPage = "transportListPage";
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
	<form id="searchForm" method="get">
		<div>
			<h2>
				<spring:message code="label.transport.tableSearchInput" />
			</h2>
			<p>
				<span class="pad_right"> <label for="transportCode">
						<spring:message code="label.transport.transportcode" />:
				</label> <c:if test="${isTransportCode}">
						<input type="text" name="transportCode" id="transportCode"
							class="autosearch">
					</c:if> <c:if test="${!isTransportCode}">
						<input type="text" name="transportCode" id="transportCode"
							class="autosearch" value="${container.getTransportCode()}">
					</c:if>
				</span> <span> <label for="routeCode"> <spring:message
							code="label.routes.routecode" />:
				</label> <c:if test="${isRoutesCode}">
						<input type="text" name="routesCode" id="routesCode"
							class="autosearch">
					</c:if> <c:if test="${!isRoutesCode}">
						<input type="text" name="routesCode" id="routesCode"
							class="autosearch" value="${container.getRoutesCode()}">
					</c:if>
				</span>
			<p>
				<span class="pad_right"> <label for="routeName"> <spring:message
							code="label.routes.routename" />:
				</label> <c:if test="${isRouteName}">
						<input type="text" name="routeName" id="routeName"
							class="autosearch">
					</c:if> <c:if test="${!isRouteName}">
						<input type="text" name="routeName" id="routeName"
							class="autosearch" value="${container.getRouteName()}">
					</c:if>
				</span> <span> <label for="priceName"><spring:message
							code="label.addtrips.maximalprice" />:</label> <c:if test="${isPrice}">
						<input class="autosearch" type="number" id="priceName"
							name="priceName">
					</c:if> <c:if test="${!isPrice}">
						<input class="autosearch" type="number" id="priceName"
							name="priceName" value="${container.getPriceName()}">
					</c:if>
				</span>
			<p>
				<br>
				<spring:message code="label.addtrips.minimalseatsbyclasses" />
				:
			<p>
				<span> <label for="seatClass1">1:</label> <c:if
						test="${isClass1}">
						<input class="autosearch" type="number" id="seatClass1"
							name="seatClass1">
					</c:if> <c:if test="${!isClass1}">
						<input class="autosearch" type="number" id="seatClass1"
							name="seatClass1" value="${container.getSeatClass1()}">
					</c:if>
				</span> <span> <label for="seatClass2">2:</label> <c:if
						test="${isClass2}">
						<input class="autosearch" type="number" id="seatClass2"
							name="seatClass2">
					</c:if> <c:if test="${!isClass2}">
						<input class="autosearch" type="number" id="seatClass2"
							name="seatClass2" value="${container.getSeatClass2()}">
					</c:if>
				</span> <span> <label for="seatClass3">3:</label> <c:if
						test="${isClass3}">
						<input class="autosearch" type="number" id="seatClass3"
							name="seatClass3">
					</c:if> <c:if test="${!isClass3}">
						<input class="autosearch" type="number" id="seatClass3"
							name="seatClass3" value="${container.getSeatClass3()}">
					</c:if>
				</span>
			<div id="btnTransportFind">
				<input type="submit"
					value="<spring:message code="label.addtrips.tripsearch"/>">
			</div>
		</div>

	</form>
	
	<h2 align="center">
		<spring:message code="label.navigation.transport" />
	</h2>

	<c:if test="${not empty errormark}">
		<font color="red"><spring:message
				code="label.transport.errormessage" /> </font>
	</c:if>

	<div id="pagingcontent"></div>

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
												priceName : $("input#priceName")
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
</section>
