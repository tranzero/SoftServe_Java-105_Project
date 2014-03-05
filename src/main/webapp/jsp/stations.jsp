<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	var defaultDomElement = "div#result";
	var defaultTargetPage = "stationsPage";
	var defaultGetData = {
		pageNumber : "${pageNumber}",
		resultsPerPage : "${resultsPerPage}",
		searchstring : "${container.getSearchString()}",
		orderByParam : "${container.getOrderByParam()}",
		orderByDirection : "${container.getOrderByDirection()}"
	};
</script>

<section id="content">
	<h2 align="center">
		<spring:message code="label.navigation.stationsForUsers" />
	</h2>

	<form method="get" class="pad_top_bottom half_width">
		Search for:
		<c:if test="${isSearchString}">
			<input name="searchstring" id="searchstring" class="autosearch"
				type="text">
		</c:if>
		<c:if test="${!isSearchString}">
			<input name="searchstring" id="searchstring" class="autosearch"
				type="text" value="${container.getSearchString()}">
		</c:if>
		<input type="submit" value="Search">
	</form>
	<div id="result">
		<div id="res_per_page" class="pagination half_width">
			<span id="res_per_page_label"><spring:message code="label.trips.resultsperpage" />
			:</span>
			<ul class="bootpag">
				<c:if test="${resultsPerPage!=10}">
					<li><a
						href="?pageNumber=1&resultsPerPage=10&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							10</a></li>
				</c:if>
				<c:if test="${resultsPerPage==10}">
					<li class="disabled"><a href="javascript:void(0);">10</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=20}">
					<li><a
						href="?pageNumber=1&resultsPerPage=20&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							20</a></li>
				</c:if>
				<c:if test="${resultsPerPage==20}">
					<li class="disabled"><a href="javascript:void(0);">20</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=50}">
					<li><a
						href="?pageNumber=1&resultsPerPage=50&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							50</a></li>
				</c:if>
				<c:if test="${resultsPerPage==50}">
					<li class="disabled"><a href="javascript:void(0);">50</a></li>
				</c:if>
			</ul>
		</div>
		<table class='table'>
			<thead>
				<tr>
					<th><spring:message code="label.stations.stationcode" />

						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=s.stationCode&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=s.stationCode&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><spring:message code="label.stations.stationname" />

						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=s.stationName&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=s.stationName&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="station" items="${stationsList}">
					<tr>
						<td align="center">${station.getStationCode()}</td>
						<td align="center">${station.getStationName()}</td>
						<td align="center"><a href="stationEdit/${station.getStationId()}">
					    	<input id="edit" type="button" name="edit"
								value="<spring:message code="label.stations.edit"/>">
						</a></td>
						<td align="center"><a href="delete/${station.getStationId()}">
							<input id="delete" type="button" name="delete"
								onclick="return confirm_delete()"
								value="<spring:message code="label.stations.delete"/>">
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							« </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByDirection=${encoder.encode(
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
							href="?pageNumber=${i}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
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
						href="??pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&orderByParam=${encoder.encode(
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
		</div>
	</div>
	<script type="text/javascript">
		$(window)
				.load(
						function() {
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
												searchstring : $(
														"input#searchstring")
														.val(),
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