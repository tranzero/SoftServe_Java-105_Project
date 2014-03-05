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
						<div>
							<img class="image_primary" src="news/images/${News.getImgSrc()}" />
						</div>
						<div class="news_content_primary">
				</c:when>
				<c:otherwise>
					<div class="news_secondary">
						<div>
							<img class="image_secondary"
								src="news/images/${News.getImgSrc()}" />
						</div>
						<div class="news_content_secondary">
				</c:otherwise>
			</c:choose>
			<div class="news_title">
				<a href="detailsnews/${News.getPostId()}">${News.getTitle()}</a>
			</div>
			<div class="news_date">${News.getDate()}</div>
			<div class="news_description">${News.getDescription().substring(0, News.getDescription().length()/2.5)}
				<form method="post" name="detailsnews" action="detailsnews">
					<input type="hidden" name="detailsId" value="${News.getPostId()}">
					<input class="detailsbutton" value="Details" name="hidden" type="submit" />
				</form>
			</div>
	</div>
	</div>
	</c:forEach>
	</div>
</c:if>
<c:if test="${empty newsList}">
	<p>No results.</p>
</c:if>