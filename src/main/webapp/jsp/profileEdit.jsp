<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<section id="content">
	<form:form action="profileEdit" commandName="user" method="post">
		<form:hidden path="userId" />
		<fieldset class="thin">
			<div class="pad_top_bottom4">
				<label for="firstName" class="thin"><spring:message
						code="label.profile.firstName" /></label>
				<form:input path="firstName" type="text" name="firstName"
					value="${user.getFirstName()}"></form:input>
				<form:errors path="firstName" cssClass="error"></form:errors>
			</div>
			<div class="pad_top_bottom4">
				<label for="lastName" class="thin"><spring:message
						code="label.profile.lastName" /></label>
				<form:input path="lastName" type="text" name="lastName"
					value="${user.getLastName()}"></form:input>
				<form:errors path="lastName" cssClass="error"></form:errors>
			</div>
			<div class="pad_top_bottom4">
				<label for="email" class="thin"><spring:message
						code="label.profile.eMail" /></label>
				<form:input path="email" type="text" name="email"
					value="${user.getEmail()}"></form:input>
				<form:errors path="email" cssClass="error"></form:errors>
			</div>
			<div class="pad_top_bottom4">
				<label for="password" class="thin"><spring:message
						code="label.profile.password" /></label>
				<form:input path="password" type="text" name="password"
					value="${user.getPassword()}"></form:input>
				<form:errors path="password" cssClass="error"></form:errors>
			</div>
			<div class="pad_top_bottom4">
				<label for="confirmPassword" class="thin">Confirm Password:</label>
				<form:input path="confirmPassword" type="text" name="password"
					value="${user.getConfirmPassword()}"></form:input>
				<form:errors path="confirmPassword" cssClass="error"></form:errors>
			</div>
		</fieldset>
		<div class="pad_top_bottom4 pad_left0">
			<input type="submit"
				value="<spring:message code="label.profile.updateProfile"/>">
		</div>
	</form:form>
</section>


