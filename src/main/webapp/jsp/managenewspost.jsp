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
	<th><spring:message code="label.navigation.news.img"/></th>
	<th></th>
	<th></th>	
	</tr>
	</thead>
	<tbody>
	<c:forEach var="News" items="${newsList}">
		<tr>
			<td>${News.getTitle()}</td>
			<td>${News.getDescription()}
				<form method="post" name="detailsnews" action="detailsnews">
					<input type="hidden" name="detailsId" value="${News.getPostId()}">
					<input class="detailsbutton" value="Details" name="hidden" type="submit" />
				</form>
			</td>
			<td>${News.getDate()}</td>
			<td>${News.getImgSrc()}</td>
			<td>
				<form id="newsIdForm" method="post" 
   						name="newsIdForm" action="editnews">  
   					<input id="newsId" type="hidden" name="newsId" value="${News.getPostId()}" />
     				<input id="newsIdButton" type="submit" value='<spring:message code="label.edit"/>' /> 
  				</form> 
			</td>
			<td>
			<form id="newsIdDelForm" method="post" 
   						name="newsIdForm" action="delnews">  
   					<input id="newsId" type="hidden" name="newsId" value="${News.getPostId()}" />
     				<input id="newsIdButton" type="submit" value='<spring:message code="label.delete"/>' />
  				</form> 
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
	<c:if test="${empty newsList}">
		<p>No results.</p>
	</c:if>
