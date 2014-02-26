<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<table style="align: center">
<tr>
				<th><div style="float: left">
						<spring:message code="label.transport.transportcode" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"> 
						<img alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.routename" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"> <img
							alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass1" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"> <img
							alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass2" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png">
						<img alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.remseatclass3" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"> <img
							alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.trips.date" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"> <img
							alt="v" src="resources/images/uparrow.png">
					</div></th>
				<th><div style="float: left">
						<spring:message code="label.transport.starttime" />
					</div>
					<div style="float: right">
						<img alt="^" src="resources/images/downarrow.png"><img
							alt="v" src="resources/images/uparrow.png">
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
			<li class="prev"><a href="javascript:void(0);"
				id="firstpage"> « </a></li>
			<li class="prev"><a href="javascript:void(0);"
				id="prevpage"> <spring:message
						code="label.prev" />
			</a></li>
		</c:if>
		<c:if test="${pageNumber==1}">
			<li class="prev disabled"><a href="javascript:void(0);"> « </a></li>
			<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
						code="label.prev" />
			</a></li>
		</c:if>
		<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
			varStatus="status">
			<c:if test="${pageNumber!=i}">
				<li><a href="javascript:void(0);"
					id="page${i}"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				id="nextpage"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				id="lastpage"> » </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>
</div>