<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<h1>List of Users</h1>

	<hr />

	<table class='table'>
	
		<thead>
			<tr>
				<th>Num</th>
				<th>Username</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>				
				<th>Role</th>
				<th>Date of Regist</th>

			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.getUserId()}</td>
					<td>${user.getUserName() }</td>
					<td>${user.getFirstName()}</td>
					<td>${user.getLastName() }</td>
					<td>${user.geteMail() }</td>					
					<td>${user.getRole().getDescription()}</td>
					<td>${user.getRegDate()}</td>

					<td><a href="userEdit/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit" value="EDIT User">
					</a></br></td>					

					<td><a href="userdel/${user.getUserId()}"> <input
							id="userdel" type="button" name="userdel" value="DELETE">
					</a></br></td>

				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	<hr />
	
</section>
