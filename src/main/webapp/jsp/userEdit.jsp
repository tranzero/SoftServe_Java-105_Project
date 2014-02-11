<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ita.edu.softserve.entity.*"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:directive.page import="java.util.*" />

<section id="content">
	<h1>Edit User</h1>
	
	<hr>

	<form id="userEdit" method="post" name="/userEdit">
		<input id="userId" type="hidden" name="userId"
			value="${user.getUserId()}">

		<div>
			<p>FirstName:</p>
			<input id="userFirstName" type="text" name="userFirstName"
				value="${user.getFirstName()}">

			<p>LastName:</p>
			<input id="lastName" type="text" name="lastName"
				value="${user.getLastName()}">

			<p>Email:</p>
			<input id="eMail" type="text" name="eMail" value="${user.geteMail()}">

			<p>Password:</p>
			<input id="passwd" type="password" name="passwd"
				value="${user.getPasswd()}">

			<p>Role:</p>
			<form:select id="role" name="role" path="user.role">
				<form:options itemLabel="description" />
			</form:select>
			
			<br/>
			<p>
				<input type="submit" value="Update USER">
				
				<input type="button" value="Cancel"
				onclick="window.location='/SoftServe_Java-105/userlist';">
			</p>
			
			<br>

		</div>

	</form>
</section>
