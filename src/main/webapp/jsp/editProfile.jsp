<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<section id="content">
	<h1>
		<spring:message code="label.profile.myProfile" />
	</h1>
	<form id="profileEdit" method="post">
		<div>
			<table>
				<tr>
					<td><spring:message code="label.profile.firstName" /></td>
					<td><input id="firstName" type="text" name="firstName"
						value="${user.getFirstName()}"></td>
				</tr>
				<tr>
					<td><spring:message code="label.profile.lastName" /></td>
					<td><input id="lastName" type="text" name="lastName"
						value="${user.getLastName()}"></td>
				</tr>
				<tr>
					<td><spring:message code="label.profile.eMail" /></td>
					<td><input id="email" type="text" name="email"
						value="${user.getEmail()}"></td>
				</tr>
				<tr>
					<td><spring:message code="label.profile.password" /></td>
					<td><input id="password" type="text" name="password"
						value="${user.getPassword()}"></td>
				</tr>
			</table>
			<input type="submit"
				value="<spring:message code="label.profile.updateProfile"/>">
		</div>
	</form>
</section>
