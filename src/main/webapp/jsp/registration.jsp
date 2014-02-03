<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
		<form action="regUser" method="post">
			<div>
				<p>UserName:</p>
				<input id="userName" type="text" name="userName">
				<p>FirstName:</p>
				<input id="firstName" type="text" name="firstName">
				<p>LastName:</p>
				<input id="lastName" type="text" name="lastName">
				<p>Email:</p>
				<input id="email" type="text" name="email">
				<p>Password:</p>
				<input id="password" type="text" name="password"> 
				<br>
				<input type="submit" value="Register"> 
			</div>
		</form>
</body>
</html>