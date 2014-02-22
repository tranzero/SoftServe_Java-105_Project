<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<c:if test="${not empty ResponsesList}">
		<hr />
		<h3>${ResponsesList.get(0).getTrip().getTransport().getTransportCode()}</h3>
		<h3>${ResponsesList.get(0).getTrip().getTransport().getRoutes().getRouteName()}</h3>
		<hr />
		<table>
			<c:forEach var="responses" items="${ResponsesList}">
				<c:if test="${responses.isChecked()}">
					<tr>
						<td>${responses.getUser().getFirstName()}</td>
						<td>${responses.getUser().getLastName().substring(0,1)}.</td>
						<td>${responses.getDate().toString().substring(0,10)}</td>
						<td>${responses.getComment()}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
</section>