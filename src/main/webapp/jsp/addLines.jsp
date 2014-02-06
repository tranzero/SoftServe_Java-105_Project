<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>All Lines</h1>
	<a class="button" href="addnewline">Add Line</a>
	<table>
		<tr>
			<td align="center">Line Name</td>
			<td align="center"></td>
			<td align="center"></td>
		</tr>
		<c:forEach var="line" items="${linesList}">
			<tr>
				<td align="center">${line.getLineName()}</td>
				<td align="center"><a href="updateline/${line.getLineName()}">edit</a></td>
				<td align="center"><a href="removeline/${line.getLineName()}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</section>