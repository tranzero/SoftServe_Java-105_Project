<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
	<c:if
		test="${not empty param.stationName1 && not empty param.stationName2}">
		
		<div class="pagination">
	<ul class="bootpag">
		<c:if test="${pageNumber>1}">
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesPage('${param.stationName1}','${param.stationName2}',1, ${resultsPerPage})"> « </a></li>
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${pageNumber-1},${resultsPerPage})"> <spring:message
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
					onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${i},${resultsPerPage})"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${pageNumber+1},${resultsPerPage})"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesPage('${param.stationName1}','${param.stationName2}',${maxPages},${resultsPerPage})"> » </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>
</div>
		</c:if>