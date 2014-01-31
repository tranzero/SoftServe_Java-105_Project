<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page With Funny News</title>
</head>
<body> -->

<div id="context">
<c:forEach var="News" items="${newsList}">
		<div>
		<p>News Title</p>
		<p>${News.getTitle()}</p>	
		<p>News Description</p>
		<p>${News.getDescription()}</p>	
		<p>News Date update</p>
		<p>${News.getDate()}</p>		
		</div>
		<br>
	</c:forEach>
	</div>
	<!-- 
</body>
</html> -->