<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>
		<spring:message code="label.lines.find" />
	</h2>
	<form:form action="linesbytwostations" method="get">
		<spring:message code="label.from" />
		<input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			<spring:message code="label.to" />
		<input type="text" name="stationName2" placeholder="Sknyliv" />
		<input class="button" type="submit" name="submit" value="<spring:message code="label.find" />" />
	</form:form>
	<div id="result">
		<%-- Results --%>
		<c:if test="${!empty LinesList}">
			<hr />
			<table>
				<tr>
					<th>N</th>
					<th><spring:message code="label.lines.linename" /> <a
						href="javascript:void(0);"
						onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${pageNumber},${resultsPerPage}, '1')"><img
							alt="^" src="resources/images/downarrow.png"></a> <a
						href="javascript:void(0);"
						onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${pageNumber},${resultsPerPage}, '2')"><img
							alt="^" src="resources/images/uparrow.png"></a></th>
					<th></th>
				</tr>
				<c:forEach var="lines" items="${LinesList}">
					<tr>
						<td id="generate"></td>
						<td>${lines.getLineName()}</td>
						<td><a href="stationsoncertainline/${lines.getLineName()}"><spring:message
									code="label.stations.showStations" /> </a></td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if
			test="${empty LinesList && not empty param.stationName1 && not empty param.stationName2}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>

		<c:if
			test="${not empty param.stationName1 && not empty param.stationName2}">
			<div class="pagination">
				<ul class="bootpag">
					<c:if test="${pageNumber>1}">
						<li class="prev"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=1&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}&submit=Find">
								« </a></li>
						<li class="prev"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}&submit=Find">
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
								href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${i}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}&submit=Find">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}&submit=Find">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?stationName1=${param.stationName1}&stationName2=${param.stationName2}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&sortOrder=${param.sortOrder}&submit=Find">
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
	<c:if
		test="${not empty param.stationName1 && not empty param.stationName2}">
		<script>
			function showLinesPage(stationName1_, stationName2_, pageNumber_,
					resultsPerPage_, sortOrder_) {

				if (stationName1_ == "" || stationName2_ == "") {
					return;
				}
				
				$.ajax(
								{
									async : true,
									beforeSend : function() {
										$("div#result")
												.html(
														'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
									},
									type : "GET",
									url : "linesbytwostationsPage",
									data : {
										stationName1 : stationName1_,
										stationName2 : stationName2_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_,
										sortOrder: sortOrder_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

			$(window).load(
					function() {
						showLinesPage("${param.stationName1}",
								"${param.stationName2}", "${pageNumber}",
								"${resultsPerPage}", "${param.sortOrder}");
					});
		</script>
	</c:if>

</section>