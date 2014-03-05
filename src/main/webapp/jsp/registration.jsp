<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%> 

<section id="content">
	<form:form action="regUser" commandName="user" method="post">
		<div class="control-group">
			<label for="userName"><spring:message code="label.reg.userName"/></label>
			<form:input path="userName" type="text" name="userName"></form:input>
			<form:errors path="userName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="firstName"><spring:message code="label.reg.firstName"/></label>
			<form:input path="firstName" type="text" name="firstName"></form:input>
			<form:errors path="firstName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="lastName"><spring:message code="label.reg.lastName"/></label>
			<form:input path="lastName" type="text" name="lastName"></form:input>
			<form:errors path="lastName" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="email"><spring:message code="label.reg.email"/></label>
			<form:input path="email" type="text" name="email"></form:input>
			<form:errors path="email" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="password"><spring:message code="label.reg.password"/></label>
			<form:input path="password" type="password" name="password"></form:input>
			<form:errors path="password" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<label for="confirmPassword"><spring:message code="label.reg.confirmPassword"/></label>
			<form:input path="confirmPassword" type="password" name="password"></form:input>
			<form:errors path="confirmPassword" cssClass="error"></form:errors>
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" value="<spring:message code="label.reg.register"/>">
			</div>
		</div>
	</form:form>
</section>
