<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- JS for confirm_delete_user -->
<script type="text/javascript">
	function confirm_delete_user() {
		return confirm('Delete this User?');
	}
</script>

<!-- userlist -->

<section id="content">

	<h2 align="center">
		<spring:message code="label.users.userList" />
	</h2>
	
	<table class='table'>

		<thead>
			<tr>
				<th><spring:message code="label.users.userName" /></th>
				<th><spring:message code="label.users.firstName" /></th>
				<th><spring:message code="label.users.lastName" /></th>
				<th><spring:message code="label.users.email" /></th>
				<th><spring:message code="label.users.role" /></th>
				<th><spring:message code="label.users.dateOfReg" /></th>
				<th></th>
				<th></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>

					<td>${user.getUserName() }</td>
					<td>${user.getFirstName()}</td>
					<td>${user.getLastName() }</td>
					<td>${user.getEmail() }</td>
					<td>${user.getRole().getDescription()}</td>
					<td>${user.getRegDate()}</td>

					<td><a href="userEdit/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit"
							value="<spring:message code="label.edit"/>">
					</a></td>

					<td><a href="userdel/${user.getUserId()}"
						onclick="return confirm_delete_user()"> <input id="userdel"
							type="button" name="userdel"
							value="<spring:message code="label.delete"/>">
					</a></td>

				</tr>
			</c:forEach>
		</tbody>

	</table>
	<hr />

</section>
