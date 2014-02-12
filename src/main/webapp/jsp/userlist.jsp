<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="true"%>

<script type="text/javascript">
	function confirm_delete_user() {
		return confirm('Delete this User?');
	}
</script>

<section id="content">
	<h1>List of Users</h1>

	<hr />

	<table class='table'>
	
		<thead>
			<tr>
				<th align="center">Num</th>
				<th align="center">Username</th>
				<th align="center">First name</th>
				<th align="center">Last name</th>
				<th align="center">Email</th>				
				<th align="center">Role</th>
				<th align="center">Date of Regist</th>

			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td align="center">${user.getUserId()}</td>
					<td align="center">${user.getUserName() }</td>
					<td align="center">${user.getFirstName()}</td>
					<td align="center">${user.getLastName() }</td>
					<td align="center">${user.geteMail() }</td>					
					<td align="center">${user.getRole().getDescription()}</td>
					<td align="center">${user.getRegDate()}</td>

					<td><a href="userEdit/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit" value="EDIT User">
					</a></br></td>					

					<td><a href="userdel/${user.getUserId()}" 
					onclick="return confirm_delete_user()"> <input
							id="userdel" type="button" name="userdel" value="DELETE">
					</a></br></td>

				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	<hr />
	
</section>
