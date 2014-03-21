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
<!-- 			<p><br> 
 -->				<label for="transportCode"> <spring:message code="label.transport.transportcode" />:</label>
				<c:if test="${isTransportCode}">
					<input type="text" name="transportCode" id="transportCode" class="autosearch" size="20">
				</c:if>
				<c:if test="${!isTransportCode}">
					<input type="text" name="transportCode" id="transportCode"
						class="autosearch" value="${container.getTransportCode()}" size="20">
				</c:if>
<!-- 			<p>
 -->				<br> <label for="routeName"> <spring:message code="label.routes.routename" />:</label>
				<c:if test="${isRouteName}">
					<input type="text" name="routeName" id="routeName" class="autosearch" size="20">
				</c:if>
				<c:if test="${!isRouteName}">
					<input type="text" name="routeName" id="routeName" class="autosearch" 
						value="${container.getRouteName()}" size="20">
				</c:if>
<!-- 			<p>
 -->				<br> <label for="routeCode"> <spring:message code="label.routes.routecode" />: </label>
				<c:if test="${isRoutesCode}">
					<input type="text" name="routesCode" id="routesCode" class="autosearch">
				</c:if>
				<c:if test="${!isRoutesCode}">
					<input type="text" name="routesCode" id="routesCode"
						class="autosearch" value="${container.getRoutesCode()}">
				</c:if>
<!-- 			<p> -->
				<br>
				<spring:message code="label.transport.minimalseatsbyclasses" />	:
			<br>
				<label for="seatClass1">1:</label>
				<c:if test="${isClass1}">
					<input class="autosearch" type="number" id="seatClass1"	name="seatClass1">
				</c:if>
				<c:if test="${!isClass1}">
					<input class="autosearch" type="number" id="seatClass1"
						name="seatClass1" value="${container.getSeatClass1()}">
				</c:if>
				<label for="seatClass2">2:</label>
				<c:if test="${isClass2}">
					<input class="autosearch" type="number" id="seatClass2"	name="seatClass2">
				</c:if>
				<c:if test="${!isClass2}">
					<input class="autosearch" type="number" id="seatClass2"
						name="seatClass2" value="${container.getSeatClass2()}">
				</c:if>
				<label for="seatClass3">3:</label>
				<c:if test="${isClass3}">
					<input class="autosearch" type="number" id="seatClass3"	name="seatClass3">
				</c:if>
				<c:if test="${!isClass3}">
					<input class="autosearch" type="number" id="seatClass3"
						name="seatClass3" value="${container.getSeatClass3()}">
				</c:if>
<!-- 			<p> -->
				<br> 
				<label for="priceName"><spring:message
						code="label.transport.maximalprice" />:</label>
				<c:if test="${isPrice}">
					<input class="autosearch" type="number" id="priceName" name="priceName">
				</c:if>
				<c:if test="${!isPrice}">
					<input class="autosearch" type="number" id="priceName"
						name="priceName" value="${container.getPriceName()}">
				</c:if><br><br>
			<div style="float: right">
				<input type="submit" value="<spring:message code="label.transport.tripsearch"/>">
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
				code="label.transport.errormessage" /> </font>
	</c:if>

	<div id="pagingcontent">

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
										prevVal = $(".autosearch").data("prevVal") || null;
										$(".autosearch").data("prevVal", curVal);
										if (prevVal !== curVal) {
											var searchData = {
												pageNumber : 1,
												resultsPerPage : "${resultsPerPage}",
												transportCode : $("input#transportCode").val(),
												routeName : $("input#routeName").val(),
												routesCode : $("input#routesCode").val(),
												seatClass1 : $("input#seatClass1").val(),
												seatClass2 : $("input#seatClass2").val(),
												seatClass3 : $("input#seatClass3").val(),
												priceName : $("input#priceName").val(),
												orderByCriteria : "${container.getOrderByCriteria()}",
												orderByDirection : "${container.getOrderByDirection()}"
											};
											ajaxLoader(defaultDomElement, defaultTargetPage, searchData);
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
