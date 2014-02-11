<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<h2>Lines find</h2>
	<form:form action="linesbytwostations" method="get">
		<%--		        <form:label path="stationName1">From:</form:label>
		        <form:input path="stationName1" />
		        <form:label path="stationName2">To:</form:label>
		        <form:input path="stationName2" />
		        --%>
			From: <input type="text" name="stationName1" placeholder="Pisochne" />&nbsp;&nbsp;&nbsp;
			To: <input type="text" name="stationName2" placeholder="Sknyliv" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	<%-- Results --%>
	<c:if test="${!empty LinesList}">
		<hr />
		<table>
			<c:forEach var="lines" items="${LinesList}">
				<tr>
					<td id="generate"></td>
					<td>${lines.getLineName()}</td>
					<td><a href="stationsoncertainline/${lines.getLineName()}">Show
							stations</a></td>
				</tr>
			</c:forEach>
		</table>
		<hr />
	</c:if>
	<c:if
		test="${empty LinesList && not empty param.stationName1 && not empty param.stationName2}">
		<hr />
		<h3>Sorry. No results was found</h3>
		<hr />
	</c:if>
</section>