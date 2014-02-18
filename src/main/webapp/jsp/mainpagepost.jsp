<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<c:if test="${!empty newsList}">
	<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
	<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>
	<div id="news_main">
		<c:forEach var="News" items="${newsList}" varStatus="status">
			<c:choose>
				<c:when test="${status.index == 0 && param.pageNumber == 0}">
					<div class="news_primary">
						<div class="test_image_primary"></div>
						<div class="news_content_primary">
				</c:when>
				<c:otherwise>
					<div class="news_secondary">
						<div class="test_image_secondary"></div>
						<div class="news_content_secondary">
				</c:otherwise>
			</c:choose>
			<div class="news_title">${News.getTitle()}</div>
			<div class="news_date">${News.getDate()}</div>
			<div class="news_description">${News.getDescription().substring(0, News.getDescription().length()/2)}
				<a href="detailsnews/${News.getPostId()}">Details...</a>
			</div>
	</div>
	</div>
	</c:forEach>
	</div>
</c:if>
<c:if test="${empty newsList}">
	<p>No results.</p>
</c:if>