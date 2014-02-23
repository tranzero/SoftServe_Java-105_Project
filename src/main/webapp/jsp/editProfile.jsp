<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h1>My Profile</h1>

<form id="editProfile" method="post">
	<div>
		<p>FirstName:</p>
		<input id="firstName" type="text" name="firstName"
			value="${user.getFirstName()}">

		<p>LastName</p>
		<input id="lastName" type="text" name="lastName"
			value="${user.getLastName()}">

		<p>Email</p>
		<input id="email" type="text" name="email" 
		    value="${user.getEmail()}">

		<p>Password</p>
		<input id="password" type="text" name="password"
			value="${user.getPassword()}">

		<p>
			<input type="submit" value="Update profile">
		</p>
	</div>
</form>

