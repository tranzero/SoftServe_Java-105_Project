<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- JS for userlist sorting -->
<link rel="shortcut icon"
	href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">
<link rel="icon"
	href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.tablesorter.min.js"></script>


<!-- JS for user searching -->
<script src="resources/js/jquery.searcher.js"></script>

<!-- JS for confirm_delete_user -->
<script type="text/javascript">
	function confirm_delete_user() {
		return confirm('Delete this User?');
	}
</script>

<!-- Search userlist -->

<c:if test="${!empty userList}">
	<h2 align="center">
		<spring:message code="label.users.userList" />
	</h2>

	<p>Search user</p>
	<p>
		<input id="inputSearchUser" type="text" />
	</p>

	<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
	<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>
	
<!-- Table userlist -->
	<table id="tableUsers">

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

					<td><a href="userEditpg/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit"
							value="<spring:message code="label.edit"/>">
					</a></td>

					<td><a href="userdelpg/${user.getUserId()}"
						onclick="return confirm_delete_user()"> <input id="userdel"
							type="button" name="userdel"
							value="<spring:message code="label.delete"/>">
					</a></td>

				</tr>
			</c:forEach>
		</tbody>

	</table>

	<!-- JS for user searching -->
	<script type="text/javascript">
		$("#tableUsers").searcher({
			inputSelector : "#inputSearchUser"
		});
	</script>

	<!-- JS for userlist Sorting -->
	<script type="text/javascript">
		$(function() {
			$("#tableUsers").tablesorter();
		});
	</script>


</c:if>
<c:if test="${empty userList}">
	<p>No results.</p>
</c:if>

