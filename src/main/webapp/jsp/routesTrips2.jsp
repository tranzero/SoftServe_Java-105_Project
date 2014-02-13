<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section id="content">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<h2 align="center">
		<spring:message code="label.navigation.routes" />
	</h2>


	<div id="routesMenu">
		<form action="routesFind" method="get">
			timeMin: <input type="text" name="timeMin"></input>
			timeMax: <input type="text" name="timeMax"></input>
			Arrive:<input name="findBy" type="radio" value="findByArr" checked>
            Departure<input name="findBy" type="radio" value="findByDep">
            <p/>
			nameStation: <input type="text" name="nameStation"></input>
			
			<input class="button" type="submit" name="submit" value ="Find"></input>
		</form>
	</div>
	
	<div id="result">
	
		<%-- Results --%>
	<c:if test="${!empty RouteTripsList}">
			<table class='tableRouteTrips'>
		<thead>
			<tr>
				<th align="center">LineName</th>
				<th align="center">RouteCode</th>
				<th align="center">StartTime</th>
			</tr>
		</thead>
	<tbody>
		<c:forEach var="routetrip" items="${RouteTripsList}">
			<tr>
				<td align="center">${routetrip.getRoute().getLineId().getLineName()}</td>
				<td align="center">${routetrip.getRoute().getRouteCode()}</td>
				<td align="center">${routetrip.getStartTime()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<c:if test="${not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
			<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=1&resultsPerPage=${resultsPerPage}">
							« </a></li>
					<li class="prev"><a
						href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
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
							href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${i}&resultsPerPage=${resultsPerPage}">
								${i} </a></li>
					</c:if>
					<c:if test="${pageNumber==i}">
						<li class="disabled"><a href="javascript:void(0);"> ${i}
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNumber<maxPages}">
					<li class="next"><a
						href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
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
			
	
	<script>
			function showRoutesTripPage(stationName_, timeMin_,timeMax_, findBy_,
					pageNumber_,
					resultsPerPage_) {

				if (stationName_ == "" || timeMin_ == "" || timeMax_ == "") {
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
									url : "routestripsbystationsPage",
									data : {
										nameStation : stationName_,
										timeMin : timeMin_,
										timeMax : timeMax_,
										findBy : findBy_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}

			$(window).load(
					function() {
						showRoutesTripPage("${param.nameStation}",
								"${param.timeMin}","${param.timeMax}","${param.findBy}",
								"${pageNumber}",
"${resultsPerPage}");
					});
		</script>
		</c:if>
</div>
</section>
