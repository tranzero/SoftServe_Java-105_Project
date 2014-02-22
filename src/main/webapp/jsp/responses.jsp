<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<c:if test="${not empty ResponsesList}">
	<table>
		<c:forEach var="responses" items="${ResponsesList}">
			<c:if test="${responses.isChecked()}">
				<tr>
					<td id="generate"></td>
					<td>${responses.getUser().getUserName()}</td>
					<td>${responses.getUser().getFirstName()}</td>
					<td>${responses.getUser().getLastName()}</td>
					<td>${responses.getDate()}</td>
					<td>${responses.getComment()}</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	</c:if>
</section>