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
				<th align="center"><spring:message code="label.users.num" /></th>
				<th align="center"><spring:message code="label.users.userName" /></th>
				<th align="center"><spring:message code="label.users.firstName" /></th>
				<th align="center"><spring:message code="label.users.lastName" /></th>
				<th align="center"><spring:message code="label.users.email" /></th>
				<th align="center"><spring:message code="label.users.role" /></th>
				<th align="center"><spring:message code="label.users.dateOfReg" /></th>

			</tr>
		</thead>

		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td align="center" id="generate"></td>
					<td align="center">${user.getUserName() }</td>
					<td align="center">${user.getFirstName()}</td>
					<td align="center">${user.getLastName() }</td>
					<td align="center">${user.getEmail() }</td>
					<td align="center">${user.getRole().getDescription()}</td>
					<td align="center">${user.getRegDate()}</td>

					<td><a href="userEditpg/${user.getUserId()}"> <input
							id="userEdit" type="button" name="userEdit" value="<spring:message code="label.edit"/>">
					</a></br></td>

					<td><a href="userdelpg/${user.getUserId()}"
						onclick="return confirm_delete_user()"> <input id="userdel"
							type="button" name="userdel" value="<spring:message code="label.delete"/>">
					</a></br></td>

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

