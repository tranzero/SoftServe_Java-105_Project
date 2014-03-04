<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<c:if test="${not empty uncheckedResponsesList}">
	<table>
		<thead>
			<tr>
				<th><spring:message code="label.users.userName" /></th>
				<th><spring:message code="label.responses.comment" /></th>
				<th><spring:message code="label.responses.date" /></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="response" items="${uncheckedResponsesList}">
				<tr>
					<td>${response.getUser().getUserName()}</td>
					<td>${response.getComment()}</td>
					<td>${response.getDate().toString().substring(0,10)}</td>
					<td><a href='markAsChecked/${response.getResponseid()}'> <input
							type="button" value="<spring:message code="label.responses.mark" />"></a></td>
					<td><a href='delResponse/${response.getResponseid()}'> <input
							type="button" value="Delete"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${empty uncheckedResponsesList}">
		<p>No results.</p>
	</c:if>
</section>