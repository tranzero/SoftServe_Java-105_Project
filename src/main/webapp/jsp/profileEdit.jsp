<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<section id="content">
	<form:form action="profileEdit" commandName="user" method="post">
        <p>
			<form:hidden path="userId" />
		<p>
		<div class="control-group">
			<label for="firstName"><spring:message code="label.profile.firstName" /></label>
			<form:input path="firstName" type="text" name="firstName" value="${user.getFirstName()}"></form:input> 
		    <form:errors path="firstName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="lastName"><spring:message code="label.profile.lastName" /></label> 
			 <form:input path="lastName" type="text" name="lastName" value="${user.getLastName()}"></form:input>
			 <form:errors path="lastName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="email"><spring:message code="label.profile.eMail" /></label>
			<form:input path="email" type="text" name="email" value="${user.getEmail()}"></form:input>
		    <form:errors path="email" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="password"><spring:message code="label.profile.password" /></label> 
			<form:input path="password" type="text" name="password" value="${user.getPassword()}"></form:input>
		     <form:errors path="password" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="confirmPassword">Confirm Password:</label>
			<form:input path="confirmPassword" type="text" name="password" value="${user.getConfirmPassword()}"></form:input>
		     <form:errors path="confirmPassword" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" value="<spring:message code="label.profile.updateProfile"/>">
			</div>
		</div>
   </form:form>
</section>


