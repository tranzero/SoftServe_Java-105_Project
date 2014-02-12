<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
