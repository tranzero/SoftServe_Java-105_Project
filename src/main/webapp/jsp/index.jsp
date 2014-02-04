<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id=context>
<a href='addnews'><input id="addnews" type="button" name="addnewsr" value="ADD News"></a>
<table>
	<thead>
	<tr>
	<th>News name</th>
	<th>News Description</th>
	<th>Publishing Date</th>
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
			<td><a href='editnews/${News.getPostId()}'><input id="editnews" type="button" name="editnews" value="EDIT News"></a></td>
			<td><a href='delnews/${News.getPostId()}'><input id="delnews" type="button" name="delnewsr" value="DELETE News"></a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
