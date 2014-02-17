<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<div id = "maxPageCount" style="display : none;">${maxPageCount}</div>
		<div id = "resultsPerPage" style="display : none;">${resultsPerPage}</div>
	<table>
	<thead>
		<tr>
			<th align="center">Line Name</th>
			<th align="center"></th>
			<th align="center"></th>
		</tr>
		</thead>
		<c:forEach var="line" items="${lines}">
			<tr>
				<td align="center">${line.getLineName()}</td>
				<td align="center"><a
					href="editline/${line.getLineName()}">edit</a></td>
				<td align="center"><a href="deleteline/${line.getLineName()}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br> <a class="button" href="addline">Add Line</a>