<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id=context>
<a href='addnews'><input id="addnews" type="button" name="addnewsr" value="ADD News"></a>
<table>
	<thead>
	<tr>
	<th>number</th>
	<th>News name</th>
	<th>News Description</th>
	<th>Date updated</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="News" items="${newsList}">
		<tr>
			<td>${News.getPostId()}</td>
			<td>${News.getTitle()}</td>
			<td>${News.getDescription()}</td>
			<td>${News.getDate()}</td>
			<td><a href='delnews/${News.getPostId()}'><input id="delnews" type="button" name="delnewsr" value="DELETE News"></a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
