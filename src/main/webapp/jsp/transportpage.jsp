<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table style="align: center">
	<thead>
		<tr>
			<th><spring:message code="label.transport.transportcode" /></th>
			<th><spring:message code="label.transport.starttime" /></th>
			<th><spring:message code="label.routes.routecode" /></th>
			<th><spring:message code="label.lines.linename" /></th>
			<th><spring:message code="label.transport.seatclass1" /></th>
			<th><spring:message code="label.transport.seatclass2" /></th>
			<th><spring:message code="label.transport.seatclass3" /></th>
			<th><spring:message code="label.transport.genprice" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${transportsList}" var="transport">
			<tr>
				<td align="center">${transport.getTransportCode()}</td>
				<td align="center">${transport.getStartTime()}</td>
				<td align="center">${transport.getRoutes().getRouteCode()}</td>
				<td align="center"><a
					href="getsLineId/${transport.getRoutes().getLineId().getLineId()}">${transport.getRoutes().getLineId().getLineName()}</a></td>
				<td align="center">${transport.getSeatclass1()}</td>
				<td align="center">${transport.getSeatclass2()}</td>
				<td align="center">${transport.getSeatclass3()}</td>
				<td align="center">${transport.getGenPrice()}</td>
				<td align="center"><a
					href="editTransport/${transport.getTransportId()}"> <input
						id="edit" type="button" name="edit"
						value="<spring:message code="label.edit"/>">
				</a></td>

				<td align="center"><a
					href="removeTransport/${transport.getTransportId()}"> <input
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
			<li class="prev"><a href="javascript:void(0);"
				onclick="showTransportPage(1, ${resultsPerPage})"> « </a></li>
			<li class="prev"><a href="javascript:void(0);"
				onclick="showTransportPage(${pageNumber-1},${resultsPerPage})">
					<spring:message code="label.prev" />
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
					onclick="showTransportPage(${i},${resultsPerPage})"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				onclick="showTransportPage(${pageNumber+1},${resultsPerPage})">
					<spring:message code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				onclick="showTransportPage(${maxPages},${resultsPerPage})"> » </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>
</div>

