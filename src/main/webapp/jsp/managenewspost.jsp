<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${!empty newsList}">
		<div id = "maxPageCount" style="display : none;">${maxPageCount}</div>
		<div id = "resultsPerPage" style="display : none;">${resultsPerPage}</div>
<p>
<a href='addnews'><input id="addnews" type="button" name="addnewsr" value="<spring:message code='label.navigation.addNews'/>"/></a>
</p>
<table>
	<thead>
	<tr>
	<th><spring:message code="label.navigation.news.title"/></th>
	<th><spring:message code="label.navigation.news.description"/></th>
	<th><spring:message code="label.navigation.news.data"/></th>
	<th></th>
	<th></th>	
	</tr>
	</thead>
	<tbody>
	<c:forEach var="News" items="${newsList}">
		<tr>
			<td>${News.getTitle()}</td>
			<td>${News.getDescription()}
				<a href="detailsnews/${News.getPostId()}">Details...</a>
			</td>
			<td>${News.getDate()}</td>
			<td><a href='editnews/${News.getPostId()}'><input id="editnews" type="button" name="editnews" value='<spring:message code="label.edit"/>'></a></td>
			<td><a href='delnews/${News.getPostId()}'><input id="delnews" type="button" name="delnewsr" value="<spring:message code="label.delete"/>"></a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
	<c:if test="${empty newsList}">
		<p>No results.</p>
	</c:if>
