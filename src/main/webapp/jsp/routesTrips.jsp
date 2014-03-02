<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="resources/css/jquery-ui.css" type="text/css" />

<section id="content">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/routesTripSearch.js"></script>


	<script>
	$(function(){
        $('#btnFindRoutes').click(
        		 function(){
        			
        	}
		);
	});	
		
	</script>
	
	<h2 align="center"><spring:message code="label.navigation.routes" /></h2>
		
		<form:form action="routesTrips" method="get">
		
			<spring:message code="label.navigation.minTime" /> <input type="text" id="timeMin" name="timeMin" ></input>
			<spring:message code="label.navigation.maxTime" /> <input type="text" id="timeMax" name="timeMax" ></input>
			<spring:message code="label.navigation.arrival" /> <input name="findBy" type="radio" value="findByArr" checked>
            <spring:message code="label.navigation.departure" /> <input name="findBy" type="radio" value="findByDep">
            <p/>
			<spring:message code="label.stations.stationname" /> <input type="text" id="stationList" name="nameStation"></input>
			<input class="button" id="btnFindRoutes" type="submit" name="submit" value =<spring:message code="label.navigation.findrouts" />></input>
			
		</form:form>
	<div id="result">
		<%-- Results --%>
		<c:if test="${!empty RoutesTripsList}">
			<hr />
				<table style="align: center">
						<tr>
							<td align="center">LineName</td>
							<td align="center">RouteCode</td>
							<td align="center">StartTime</td>
						</tr>
					<c:forEach var="routetrip" items="${RoutesTripsList}">
						<tr>
							<td align="center">${routetrip.getRoute().getLineId().getLineName()}</td>
							<td align="center">${routetrip.getRoute().getRouteCode()}</td>
							<td align="center">${routetrip.getStartTime()}</td>
						</tr>
					</c:forEach>
				</table>
				<hr />
		</c:if>
		<c:if
				test="${empty RoutesTripsList && not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
				<hr />
					<h3>Sorry. No results was found</h3>
				<hr />
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
		</c:if>
	</div>
	
	<c:if test="${not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
	<script>
		$(function(){
		  	getStationList();
		});	
		function showRoutesTripPage(stationName_, timeMin_, timeMax_, findBy_,
				pageNumber_, resultsPerPage_) {
			if (stationName_ == "" || timeMin_ == "" || timeMax_ == "") {
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
								url : "routesTripsPage",
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
							"${param.timeMin}", "${param.timeMax}",
							"${param.findBy}", "${pageNumber}",
							"${resultsPerPage}");
				});
	</script>
	</c:if>
</section>
