<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
<script>
var defaultDomElement = "div#result"; 
var defaultTargetPage = "tripspage"; 
var defaultGetData={
		pageNumber: "${pageNumber}",
		resultsPerPage: "${resultsPerPage}",
		transportCode: "${container.getTransportCode()}",
		routeName: "${container.getRouteName()}",
		remSeatClass1: "${container.getRemSeatClass1()}",
		remSeatClass2: "${container.getRemSeatClass2()}",
		remSeatClass3: "${container.getRemSeatClass3()}",
		minDate: "${container.getMinDateString()}",
		maxDate: "${container.getMaxDateString()}",
		orderByParam: "${container.getOrderByParam()}",
		orderByDirection :"${container.getOrderByDirection()}"
};

</script>
	<h2>
		<spring:message code="label.navigation.trips" />
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
				<input class="autosearch" type="text" id="transportCode" name="transportCode">
			</c:if>
			<c:if test="${!isTransportCode}">
				<input class="autosearch" type="text" id="transportCode" name="transportCode"
					value="${container.getTransportCode()}">
			</c:if>
			<spring:message code="label.trips.routename" />
			:
			<c:if test="${isRouteName}">
				<input class="autosearch" type="text" id="routeName" name="routeName">
			</c:if>
			<c:if test="${!isRouteName}">
				<input class="autosearch" type="text" id="routeName" name="routeName"
					value="${container.getRouteName()}">
			</c:if>
		<p>
			<spring:message code="label.trips.minimalremainingseatsbyclasses" />
			:
		<p>
			<label for="remSeatClass1">1:</label>
			<c:if test="${isClass1}">
				<input class="autosearch" type="number" id="remSeatClass1" name="remSeatClass1">
			</c:if>
			<c:if test="${!isClass1}">
				<input class="autosearch" type="number" id="remSeatClass1" name="remSeatClass1"
					value="${container.getRemSeatClass1()}">
			</c:if>
			<label for="remSeatClass2">2:</label>
			<c:if test="${isClass2}">
				<input class="autosearch" type="number" id="remSeatClass2" name="remSeatClass2">
			</c:if>
			<c:if test="${!isClass2}">
				<input class="autosearch" type="number" id="remSeatClass2" name="remSeatClass2"
					value="${container.getRemSeatClass2()}">
			</c:if>
			<label for="remSeatClass3">3:</label>
			<c:if test="${isClass3}">
				<input class="autosearch" type="number" id="remSeatClass3" name="remSeatClass3">
			</c:if>
			<c:if test="${!isClass3}">
				<input class="autosearch" type="number" id="remSeatClass3" name="remSeatClass3"
					value="${container.getRemSeatClass3()}">
			</c:if>
		<p>
			<spring:message code="label.trips.daterange" />
			:
		<p>
			<label for="minDate"><spring:message
					code="label.addtrips.from" /></label>
			<c:if test="${isMinDate}">
				<input class="autosearch" type="text" id="from" name="minDate">
			</c:if>
			<c:if test="${!isMinDate}">
				<input class="autosearch" type="text" id="from" name="minDate"
					value="${container.getMinDateString()}">
			</c:if>


			<label for="maxDate"><spring:message code="label.addtrips.to" /></label>
			<c:if test="${isMaxDate}">
				<input class="autosearch" type="text" id="to" name="maxDate">
			</c:if>
			<c:if test="${!isMaxDate}">
				<input class="autosearch" type="text" id="to" name="maxDate"
					value="${container.getMaxDateString()}">
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
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.transportCode&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.transportCode&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.routename" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.routes.routeName&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.routes.routeName&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass1" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass1&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass1&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass2" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass2&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass2&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass3" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass3&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.remSeatClass3&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.date" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.startDate&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.startDate&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.transport.starttime" />
					</div>
					<div style="float: right">
						<a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.startTime&orderByDirection=ASC">
							<img alt="^" src="resources/images/downarrow.png">
						</a> <a
							href="?resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())
						}&orderByParam=tr.transport.startTime&orderByDirection=DESC">
							<img alt="v" src="resources/images/uparrow.png">
						</a>
					</div></th>
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
				</tr>
			</c:forEach>
		</table>

		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							« </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
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
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
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
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
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
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
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
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							20</a></li>
				</c:if>
				<c:if test="${resultsPerPage==20}">
					<li class="disabled"><a href="javascript:void(0);">20</a></li>
				</c:if><c:if test="${resultsPerPage!=50}">
					<li><a
						href="?resultsPerPage=50&transportCode=${encoder.encode(
						container.getTransportCode())}&routeName=${encoder.encode(
						container.getRouteName())}&remSeatClass1=${
						container.getRemSeatClass1()}&remSeatClass2=${
						container.getRemSeatClass2()}&remSeatClass3=${
						container.getRemSeatClass3()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
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
 			$("input#remSeatClass1").ForceNumericOnly();
 			$("input#remSeatClass2").ForceNumericOnly();
 			$("input#remSeatClass3").ForceNumericOnly();
 			setInterval(function(){
 				var curVal;
 				var prevVal;
 			    curVal = $(".autosearch").serialize();
 			    prevVal  = $(".autosearch").data("prevVal") || null;
 			    $(".autosearch").data("prevVal",curVal);
 			    if ((prevVal!= null) && (prevVal !== curVal)) {
 			    	var searchData={
 			    			pageNumber: 1,
 			    			resultsPerPage: "${resultsPerPage}",
 			    			transportCode: $("input#transportCode").val(), 
 			    			routeName: $("input#routeName").val(), 
 			    			remSeatClass1: $("input#remSeatClass1").val(),
 			    			remSeatClass2: $("input#remSeatClass2").val(),
 			    			remSeatClass3: $("input#remSeatClass3").val(),
 			    			minDate: $("input#from").val(),
 			    			maxDate: $("input#to").val(),
 			    			orderByParam: "${container.getOrderByParam()}",
 			    			orderByDirection :"${container.getOrderByDirection()}"
 			    	};
 			    	ajaxLoader(defaultDomElement, defaultTargetPage, searchData);
 			    }
 			}, 3000);
		});
	</script>
</section>