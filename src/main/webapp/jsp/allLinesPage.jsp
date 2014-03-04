<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>
<p><a class="button" href="addline"><spring:message code="label.lines.addlines"/></a></p>
<table style="align: center">
	<thead>
		<tr>
			<th align="center"><spring:message code="label.lines.linename" /> <a
				href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber},${resultsPerPage}, '1')"><img
					alt="^" src="resources/images/downarrow.png"></a> <a
				href="javascript:void(0);"
				onclick="showLinesPage(${pageNumber},${resultsPerPage}, '2')"><img
					alt="^" src="resources/images/uparrow.png"></a></th>
			<th align="center"></th>
			<th align="center"></th>
		</tr>
	</thead>
	<c:forEach var="line" items="${lines}">
		<tr>
			<td align="center">${line.getLineName()}</td>
			<td align="center"><a href="editline/${line.getLineId()}"><input type="button" value="<spring:message code="label.lines.editline"/>"></a></td>
			<td align="center"><a href="deleteline/${line.getLineId()}"><input type="button" value="<spring:message code="label.lines.deleteline"/>"></a></td>
		</tr>
	</c:forEach>
</table>
