<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section id="content">
	<h1>Edit2 User</h1>
	<hr />

	<hr>

	<form id="userEdit2" method="post" name="/userEdit2">
		<input id="userId" type="hidden" name="userId"
			value="${user.getUserId()}">

		<div>
			<p>FirstName:</p>
			<input id="userFirstName" type="text" name="userFirstName"
				value="${user.getFirstName()}">

			<p>LastName</p>
			<input id="lastName" type="text" name="lastName"
				value="${user.getLastName()}">

			<p>Email</p>
			<input id="eMail" type="text" name="eMail" value="${user.geteMail()}">

			<p>Password</p>
			<input id="password" type="password" name="password"
				value="${user.getPassword()}">

			<p>
				<input type="submit" value="Update USER">
				
				<input type="button" value="Cancel"
				onclick="window.location='/SoftServe_Java-105/userlist';">
			</p>
			<br>

		</div>

	</form>
</section>
