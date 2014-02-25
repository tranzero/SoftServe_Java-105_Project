<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<h2 align="center">
		<spring:message code="label.navigation.route" />
	</h2>
	<div id="pagingcontent">
		<table style="align: center">
			<thead>
				<tr>
					<th><spring:message code="label.routes.routecode" /></th>
					<th><spring:message code="label.lines.linename" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="route" items="${routesList}">
					<tr>
						<td align="center">${route.getRouteCode()}</td>
						<td align="center">${route.getLineId().getLineName()}</td>
						<td align="center"><a
							href="editRoute/${route.getRouteId()}"> <input
								id="edit" type="button" name="edit"
								value="<spring:message code="label.edit"/>">
						</a></td>

						<td align="center"><a
							href="deleteRoute/${route.getRouteId()}"> <input
								id="delete" type="button" name="delete"
								onclick="return confirm_delete()"
								value="<spring:message code="label.delete"/>">
						</a></td>
				</c:forEach>
			</tbody>
		</table>
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
	
<script type="text/javascript">
	function confirm_delete() {
		return confirm('Are you sure?');
	}
</script>
	<script>

		function showRoutePage(pageNumber_, resultsPerPage_) {
			$
					.ajax(
							{
								async : true,
								beforeSend : function() {
									$("div#pagingcontent")
											.html(
													'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
								},
								type : "GET",
								url : "routesAllEditPage",
								data : {
									pageNumber : pageNumber_,
									resultsPerPage : resultsPerPage_
								}

							}).done(function(msg) {
						$("div#pagingcontent").html(msg);
					});

		}
		

		$(window).load(function() {
			showTransportPage("${pageNumber}", "${resultsPerPage}");
		});
	</script>
</section>
