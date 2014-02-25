<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<section id="content">
	<form action="regUser" method="post">
		<div class="control-group">
			<label for="userName">UserName:</label>
			<input id="userName" type="text" name="userName">
		</div>
		<div class="control-group">
			<label for="firstName">FirstName:</label>
			<input id="firstName" type="text" name="firstName">
		</div>
		<div class="control-group">
			<label for="lastName">LastName:</label>
			<input id="lastName" type="text" name="lastName">
		</div>
		<div class="control-group">
			<label for="email">Email:</label>
			<input id="email" type="text" name="email">
		</div>
		<div class="control-group">
			<label for="password">Password:</label>
			<input id="password" type="text" name="password">
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" value="Register">
			</div>
		</div>
	</form>
</section>