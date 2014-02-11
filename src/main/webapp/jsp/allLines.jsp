<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>ALL LINES</h1>
	<div id="result">
	<table>
		<tr>
			<td align="center">Line Name</td>
			<td align="center"></td>
			<td align="center"></td>
		</tr>
		<c:forEach var="line" items="${lines}">
			<tr>
				<td align="center">${line.getLineName()}</td>
				<td align="center"><a
					href="editline/${line.getLineName()}">edit</a></td>
				<td align="center"><a href="deleteline/${line.getLineName()}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br> <a class="button" href="addline">Add Line</a>
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
					url : "allLinesPage",
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