<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>All Lines</h1>
	<%
		int i = 0;
	%>
	<a class="button" href="addNewLine">Add Line</a>
	<table border="1">
		<tr>
			<td align="center">Line Name</td>
			<td align="center"></td>
			<td align="center"></td>
		</tr>
		<c:forEach var="line" items="${linesList}">
			<tr>
				<td align="center">${line.getLineName()}</td>
				<td align="center"><a href="updateLines/${line.getLineName()}">edit</a></td>
				<td align="center"><a href="removeLines/${line.getLineName()}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</section>