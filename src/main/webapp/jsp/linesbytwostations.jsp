<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Lines find</h2>
	<form:form action="linesbytwostations" method="get">
		<%--		        <form:label path="stationName1">From:</form:label>
		        <form:input path="stationName1" />
		        <form:label path="stationName2">To:</form:label>
		        <form:input path="stationName2" />
		        --%>
			From: <input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder="Sknyliv" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	<div id="result">
	<%-- Results --%>
	<c:if test="${!empty LinesList}">
		<hr />
		<table>
			<c:forEach var="lines" items="${LinesList}">
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
	<c:if
		test="${empty LinesList && not empty param.stationName1 && not empty param.stationName2}">
		<hr />
		<h3>Sorry. No results was found</h3>
		<hr />
	</c:if>
	</div>
		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}"> « </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
							href="?pageNumber=${i}&resultsPerPage=${resultsPerPage}">
								${i} </a></li>
					</c:if>
					<c:if test="${pageNumber==i}">
						<li class="disabled"><a href="javascript:void(0);"> ${i}
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNumber<maxPages}">
					<li class="next"><a
						href="?pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
			<script>
		function showLinesPage(pageNumber_, resultsPerPage_) {

			$.ajax({
				async : true,
				beforeSend : function() {
					$("div#result")
					.html(
							'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
					},
					type : "GET",
					url : "linesbytwostationsPage",
					data : {
						pageNumber : pageNumber_,
						resultsPerPage : resultsPerPage_
						}
					}).done(function(msg) {
						$("div#result").html(msg);
						});

			
		}

		$(window).load(function() {

			showTripsPage("${pageNumber}", "${resultsPerPage}");

		});
		
	</script>
</section>