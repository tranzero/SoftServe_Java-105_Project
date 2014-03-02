<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<section id="content">
	<form:form action="regUser" commandName="user" method="post">
		<div class="control-group">
			<label for="userName">UserName:</label>
			<form:input path="userName" type="text" name="userName"></form:input>
			<form:errors path="userName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="firstName">FirstName:</label>
			<form:input path="firstName" type="text" name="firstName"></form:input>
			<form:errors path="firstName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="lastName">LastName:</label>
			<form:input path="lastName" type="text" name="lastName"></form:input>
			<form:errors path="lastName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="email">Email:</label>
			<form:input path="email" type="text" name="email"></form:input>
			<form:errors path="email" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="password">Password:</label>
			<form:input path="password" type="text" name="password"></form:input>
			<form:errors path="password" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="confirmPassword">Confirm Password:</label>
			<form:input path="confirmPassword" type="text" name="password"></form:input>
			<form:errors path="confirmPassword" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" value="Register">
			</div>
		</div>
	</form:form>
</section>
