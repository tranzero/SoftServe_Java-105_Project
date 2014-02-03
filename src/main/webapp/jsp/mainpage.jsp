<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id=context>
<table>
	<thead>
	<tr>
	<th>News name</th>
	<th>News Description</th>
	<th>Date updated</th>
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
			
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
