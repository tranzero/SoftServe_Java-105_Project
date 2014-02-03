<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
<title>Lines Service</title>
<%-- temporary styles --%>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<%-- temporary --%>
	<header>
	
	<div id="menu">
		<nav class="site-nav">
		<ul>
			<li class="current_page_item"><a href="">Homepage</a></li>
			<li><a href="">Menu Item #0</a></li>
			<li><a href="">Menu Item #1</a></li>
			<li><a href="">Menu Item #2</a></li>
			<li><a href="">Menu Item #3</a></li>
		</ul>
		</nav>
	</div>
	</header>-->
<div id="context">
	<link href="<c:url value="/resources/css/style.css" />"
		rel="stylesheet" type="text/css" />
	<section>
		<!-- 	<h1 class="logo">Lines Service</h1>  -->
		<%-- end temporary --%>
		<form action="linesFind" method="get">
			From: <input type="text" name="stationName1" placeholder='Pisochne' />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder='Sknyliv' />
			<input class="button" type="submit" name="submit" value="Find" />
		</form>
		<%-- Results --%>
		<c:if test="${!empty LinesList}">
			<hr />
			<table>
				<c:forEach var="lines" items="${LinesList}">
					<tr>
						<td id="generate"></td>
						<td>${lines.getLineName()}</td>
						<td><a href="stationsoncertainline/${lines.getLineName()}">Show stations</a></td>
					</tr>
				</c:forEach>
			</table>
			<hr />
		</c:if>
		<c:if test="${empty LinesList}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>
		<%-- Results end --%>
		<%-- temporary --%>
		<!-- <div class="pagination">
		<span class="page-numbers current">1</span>
		<a href="" class="page-numbers">2</a>
		<a href="" class="page-numbers">3</a>
		<a href="" class="page-numbers">4</a>
		<a href="" class="next page-numbers">Next »</a>
	</div>-->
		<%-- end temporary --%>
	</section>
</div>
<!--  
</body>
</html>-->