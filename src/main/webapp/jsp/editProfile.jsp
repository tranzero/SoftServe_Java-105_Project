<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<section id="content">
	<form id="profileEdit" method="post">
		<div class="control-group">
			<label for="firstName"><spring:message code="label.profile.firstName" /></label>
			<input id="firstName" type="text" name="firstName" value="${user.getFirstName()}">
		</div>
		<div class="control-group">
			<label for="lastName"><spring:message code="label.profile.lastName" /></label> 
			<input id="lastName" type="text" name="lastName" value="${user.getLastName()}">
		</div>
		<div class="control-group">
			<label for="email"><spring:message code="label.profile.eMail" /></label>
			<input id="email" type="text" name="email" value="${user.getEmail()}">
		</div>
		<div class="control-group">
			<label for="password"><spring:message code="label.profile.password" /></label> 
			<input id="password" type="text" name="password" value="${user.getPassword()}">
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" value="<spring:message code="label.profile.updateProfile"/>">
			</div>
		</div>
	</form>
</section>
