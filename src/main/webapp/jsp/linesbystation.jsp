<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	int i = 0;
%>
<!-- <script type="text/javascript">
var pageUrl = "linesbystationresult";
</script> -->
<h1>Lines Search</h1>
<section id="content">
	<form name="linesbystation" id="linebystation" method="get">
		Enter station name :
		<c:if test="${not empty param.stationName}">
			<input type="text" name="stationName" value="${param.stationName}" />
		</c:if>
		<c:if test="${empty param.stationName}">
			<input type="text" name="stationName"/>
		</c:if>
		<input class="button" type="submit" name="submit" value="Find" />
	</form>
	<div id="result">
		<c:if test="${!empty linesbystationlist}">
			<hr />
			<table>

				<c:forEach var="lines" items="${linesbystationlist}">
					<tr>
						<td id="generate"></td>
						<td>${lines.getLineName()}</td>
						<td><a href="stationsoncertainline/${lines.getLineName()}">Show
								stations</a></td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if test="${empty linesbystationlist}">
			<p>No results.</p>
		</c:if>
		<c:if test="${not empty param.stationName}">
			<div class="pagination">
				<ul class="bootpag">
					<c:if test="${pageNumber>1}">
						<li class="prev"><a
							href="?stationName=${param.stationName}&pageNumber=1&resultsPerPage=${resultsPerPage}">
								« </a></li>
						<li class="prev"><a
							href="?stationName=${param.stationName}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
								href="?stationName=${param.stationName}&pageNumber=${i}&resultsPerPage=${resultsPerPage}">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?stationName=${param.stationName}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?stationName=${param.stationName}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
		</c:if>
	</div>
	<c:if test="${not empty param.stationName}">
		<script>
			function showLinesByStationPage(stationName_, pageNumber_,
					resultsPerPage_) {

				if (stationName_ == "") {
					return;
				}
				$
						.ajax(
								{
									async : true,
									beforeSend : function() {
										$("div#result")
												.html(
														'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
									},
									type : "GET",
									url : "linesbystationresult",
									data : {
										stationName : stationName_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

			$(window).load(
					function() {
						showLinesByStationPage("${param.stationName}",
								"${pageNumber}", "${resultsPerPage}");
					});
		</script>

	</c:if>

</section>
