<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<h1>Edit User</h1>
	<hr />

	<hr>

	<form id="userEdit" method="post" name="useEdit">
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
			<input id="Passwd" type="text" name="Passwd"
				value="${user.getPasswd()}">

			<p>Role</p>
			<select>
				<c:forEach var="user" items="user">
					<option selected value="${user.getRole()}">${user.getRole()}</option>
					<option value="${Role.REGUSER}">REGUSER</option>
					<option value="ADMIN">ADMIN</option>
					<option value="MANAGER">MANAGER</option>
				</c:forEach>
			</select>


			<p>
				<input type="submit" value="Update USER">
			</p>
			<br>

		</div>


	</form>
</div>
<!-- </body> -->
<!-- </html> -->