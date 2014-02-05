<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<h1>All Lines</h1>
	<%
		int i = 0;
	%>
	<a class="button" href="addNewLine">Add Line</a>
	<table border="1">
		<tr>
			<td align="center">Number</td>
			<td align="center">Line Name</td>
		</tr>
		<c:forEach var="line" items="${linesList}">
			<tr>
				<td align="center"><%=++i%></td>
				<td align="center">${line.getLineName()}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- </body> -->
<!-- </html> -->