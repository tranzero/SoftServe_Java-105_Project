<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<c:if test="${not empty ResponsesList}">
		<div>
			<h3>${ResponsesList.get(0).getTrip().getTransport().getTransportCode()}
				-
				${ResponsesList.get(0).getTrip().getTransport().getRoutes().getRouteName()}</h3>
		</div>
		<c:forEach var="responses" items="${ResponsesList}">
			<c:if test="${responses.isChecked()}">
				<div class="response">
					<div id="postedBy">
						<div class="avatar">
							<span class="user_avatar"></span>
						</div>
						<div class="user_name">${responses.getUser().getFirstName()}
							${responses.getUser().getLastName().substring(0,1)}.</div>
						<div class="response_date">${responses.getDate().toString().substring(0,10)}</div>
					</div>
					<div class="commentText">${responses.getComment()}</div>
				</div>
			</c:if>
		</c:forEach>
	</c:if>
</section>