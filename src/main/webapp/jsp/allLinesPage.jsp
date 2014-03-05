<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Results --%>
<c:if test="${!empty Lines}">
	<hr />
	<table>
		<tr>
			<td><spring:message code="label.lines.linename" /> <a
				href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber},${resultsPerPage}, '1')"><img
					alt="^" src="resources/images/downarrow.png"></a> <a
				href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber},${resultsPerPage}, '2')"><img
					alt="^" src="resources/images/uparrow.png"></a></td>
			<td><spring:message code="label.lines.editline"/></td>
			<td><spring:message code="label.lines.deleteline"/></td>
		</tr>
		<c:forEach var="lines" items="${Lines}">
			<tr>
				<td>${lines.getLineName()}</td>
				<td align="center"><a href="editline/${lines.getLineId()}"><input
						type="button"
						value="<spring:message code="label.lines.editline"/>"></a></td>
				<td align="center"><a href="deleteline/${lines.getLineId()}"><input
						type="button"
						value="<spring:message code="label.lines.deleteline"/>"></a></td>
			</tr>
		</c:forEach>
	</table>
	<a class="button" href="addline"><spring:message code="label.lines.addlines"/></a>
	<hr />
</c:if>
<div class="pagination">
	<ul class="bootpag">
		<c:if test="${pageNumber>1}">
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesPage(1, ${resultsPerPage}, '${param.sortOrder}')">
					« </a></li>
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber-1},${resultsPerPage}, '${param.sortOrder}')">
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
					onclick="showLinesPage(${i},${resultsPerPage}, '${param.sortOrder}')">
						${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber+1},${resultsPerPage}, '${param.sortOrder}')">
					<spring:message code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesPage(${maxPages},${resultsPerPage}, '${param.sortOrder}')">
					» </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>
</div>