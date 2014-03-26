<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<section id="content">
	<c:if test="${not empty ResponsesList}">
		<div id="tripInfo">
			<h3>${ResponsesList.get(0).getTrip().getTransport().getRoutes().getRouteName()}
				(${ResponsesList.get(0).getTrip().getTransport().getTransportCode()})</h3>
			<div id="live_resp_btn">
				<a
					href="/SoftServe_Java-105/addComment/${ResponsesList.get(0).getTrip().getTripId()}"><input
					type="button"
					value="<spring:message code="label.responses.leaveComment" />" /></a>
			</div>
		</div>
		<c:forEach var="responses" items="${ResponsesList}">
			<c:if test="${responses.isChecked()}">
				<div id="response">
					<div id="postedBy">
						<span class="user_avatar"></span>
						<div class="user_name">${responses.getUser().getFirstName()}
							${responses.getUser().getLastName().substring(0,1)}. <span
								class="response_date">${responses.getDate().toString().substring(0,10)}</span>
						</div>
						<div class="commentText">${responses.getComment()}</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</c:if>
	<c:if test="${empty ResponsesList}">
		<p>Sorry. There is no comments for this trip.</p>
	</c:if>
</section>