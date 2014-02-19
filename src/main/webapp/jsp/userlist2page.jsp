<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- JS for table sorting -->
<link rel="shortcut icon"
	href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">
<link rel="icon"
	href="http://d15dxvojnvxp1x.cloudfront.net/assets/favicon.ico">

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.tablesorter.min.js"></script>


<!-- JS for table searching -->
<script src="resources/js/jquery.searcher.js"></script>

<!-- JS for confirm_delete_user -->
<script type="text/javascript">
	function confirm_delete_user() {
		return confirm('Delete this User?');
	}
</script>

<!-- userlist -->


<c:if test="${!empty userList}">

	<p>Search user</p>
	<p>
		<input id="inputSearchUser" />
	</p>

	<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
	<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>

	<table id="tableUsers">

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

					<td><a href="userEditpg/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit" value="EDIT User">
					</a></br></td>

					<td><a href="userdelpg/${user.getUserId()}"
						onclick="return confirm_delete_user()"> <input id="userdel"
							type="button" name="userdel" value="DELETE">
					</a></br></td>

				</tr>
			</c:forEach>
		</tbody>

	</table>

	<!-- JS for table searching -->
	<script type="text/javascript">
		$("#tableUsers").searcher({inputSelector : "#inputSearchUser"});
	</script>


</c:if>
<c:if test="${empty userList}">
	<p>No results.</p>
</c:if>

