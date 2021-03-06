<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.users.userEdit" />
	</h2>

	<form:form method="post" action="userEdit2.htm" commandName="user">
		<fieldset>

			<form:hidden path="userId" />

			<div class="data">
				<label><spring:message code="label.users.firstName" /><em
					class="star">*</em>:</label>
				<form:input type="text" path="firstName" onfocus="checkpostal()" />
				<form:errors path="firstName" cssClass="error" />
			</div>
			<p>
			<div class="data">
				<label><spring:message code="label.users.lastName" /><em
					class="star">*</em>:</label>

				<form:input type="text" path="lastName" onfocus="checkpostal()" />
				<form:errors path="lastName" cssClass="error" />
			</div>
			<p>
			<div class="data">
				<label><spring:message code="label.users.email" /><em
					class="star">*</em>:</label>

				<form:input type="text" path="email" onfocus="checkpostal()" />
				<form:errors path="email" cssClass="error" />
			</div>
			<p>
			<div class="data">
				<label><spring:message code="label.users.password" /><em
					class="star">*</em>:</label>
				<form:input type="password" path="password" onfocus="checkpostal()" />
				<form:errors path="password" cssClass="error" />
			</div>
			<p>
			<div class="data">
				<label><spring:message code="label.users.confirmPassword" /><em
					class="star">*</em>:</label>
				<form:input path="confirmPassword" type="password"
					onfocus="checkpostal()" />
				<form:errors path="confirmPassword" cssClass="error"></form:errors>
			</div>
			<p>
			<div class="data">
				<label> <spring:message code="label.users.role" /><em
					class="star">*</em>:
				</label>
				<form:select id="role" path="role" class="selectRole">
					<option value="ADMIN"><label> <spring:message code="label.admin" /></option>
					<option value="MANAGER"><label> <spring:message code="label.manager" /></option>
					<option value="REGUSER"><label> <spring:message code="label.reguser" /></option>
				</form:select>
				<form:errors path="role" cssClass="error" />
			</div>
			<p>
		</fieldset>
		<p>
		<div>
			<input name="control" type="submit"
				value="<spring:message code ="label.update" />" /> <input
				type="button" value="<spring:message code="label.cancel"/>"
				onclick="window.location='/SoftServe_Java-105/userlist2';">
		</div>

	</form:form>
	<p>
		<em class="star">*</em> —
		<spring:message code="label.star" />

		<script src="resources/js/jquery.js"></script>
</section>

