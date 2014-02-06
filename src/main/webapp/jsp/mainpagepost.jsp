<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

	<c:if test="${!empty newsList}">
		<div id = "maxPageCount" style="display : none;">${maxPageCount}</div>
		<div id = "resultsPerPage" style="display : none;">${resultsPerPage}</div>
		<table>
			<thead>
				<tr>
					<th>
						News title
					</th>
					<th>
						News description
					</th>
					<th>
						News date
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="News" items="${newsList}">
					<tr>
						<td>
							${News.getTitle()}
						</td>
						
						<td>
							${News.getDescription()}
							<a href="detailsnews/${News.getPostId()}">Details...</a>
						</td>
						<td>
							${News.getDate()}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty newsList}">
		<p>No results.</p>
	</c:if>