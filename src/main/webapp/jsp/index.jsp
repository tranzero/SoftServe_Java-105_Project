<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page With Funny News</title>
</head>
<body>
<div>
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
			
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>