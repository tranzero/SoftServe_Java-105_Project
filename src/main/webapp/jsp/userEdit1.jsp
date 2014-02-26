<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ita.edu.softserve.entity.*"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="resources/js/usersEdit-validation.js"></script>

<jsp:directive.page import="java.util.*" />

<section id="content">
	<h2 align="center">
		<spring:message code="label.users.userEdit" />
	</h2>
	<hr />

	<form id="userEdit" method="post" action="" name="/userEdit"
		style="text-align: left">

		<input id="userId" type="hidden" name="userId"
			value="${user.getUserId()}">

		<div>
			<p>
				<spring:message code="label.users.firstName" />
				: <input id="userFirstName" type="text" name="userFirstName"
					value="${user.getFirstName()}">
			</p>

			<p>
				<spring:message code="label.users.lastName" />
				: <input id="lastName" type="text" name="lastName"
					value="${user.getLastName()}"><br>
			</p>

			<p>
				<spring:message code="label.users.email" />
				: <input id="email" type="text" name="email"
					value="${user.getEmail()}">
			</p>



			<p>
				<spring:message code="label.users.password" />
				: <input id="password" type="password" name="password"
					value="${user.getPassword()}">
			</p>

			<p>
				<spring:message code="label.users.role" />
				:

				<form:select id="role" name="role" path="user.role">
					<form:options itemLabel="description" />
				</form:select>
			</p>

			<br>

			<p align="center">
				<input type="submit"
					value="<spring:message
					code="label.update" />"> <input
					type="button" value="<spring:message
					code="label.cancel" />"
					onclick="window.location='/SoftServe_Java-105/userlist2';">
			</p>

			<br>

		</div>

	</form>
</section>
