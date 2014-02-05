<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF8"> -->
<!-- <title>Find lines by certain Station</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<%
			int i = 0;
		%>
<h1 >Lines Search</h1>
<form:form action="findlinesbyStation" method="get">
		Enter station name : <input type="text" name="stationname" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	 <c:if test="${!empty linesbystationlist}">
		<hr />
		<table>
			<c:forEach var="lines" items="${linesbystationlist}">
				<tr>
					<td><%=++i%></td>
					<td>${lines.getLineName()}</td>
					<td><a href="stationsoncertainline/${lines.getLineName()}">Show stations</a></td>
				</tr>
			</c:forEach>
		</table>
		<hr />
	</c:if>
		</div>
<!-- </body> -->
<!-- </html> -->