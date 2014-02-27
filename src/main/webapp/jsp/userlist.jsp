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
	<form method="get">

		Search for:
		<c:if test="${isSearchString}">
			<input name="searchstring" id="searchstring" class="autosearch"
				type="text">
		</c:if>
		<c:if test="${!isSearchString}">
			<input name="searchstring" id="searchstring" class="autosearch"
				type="text" value="${container.getSearchString()}">
		</c:if>
		<p>
			<br> Admin:
			<c:if test="${container.getIsAdmin()}">
				<input type="checkbox" name="isAdmin" id="isAdmin"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsAdmin()}">
				<input type="checkbox" name="isAdmin" id="isAdmin"
					class="autosearch">
			</c:if>

			Manager: <c:if test="${container.getIsManager()}">
				<input type="checkbox" name="isManager" id="isManager"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsManager()}">
				<input type="checkbox" name="isManager" id="isManager"
					class="autosearch">
			</c:if>
			Registered user: <c:if test="${container.getIsRegUser()}">
				<input type="checkbox" name="isRegUser" id="isRegUser"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsRegUser()}">
				<input type="checkbox" name="isRegUser" id="isRegUser"
					class="autosearch">
					</c:if>
		<p>
			<spring:message code="label.trips.daterange" />
			:
		<p>
			<label for="minDate"><spring:message
					code="label.addtrips.from" /></label>
			<c:if test="${isMinDate}">
				<input class="autosearch" type="text" id="from" name="minDate">
			</c:if>
			<c:if test="${!isMinDate}">
				<input class="autosearch" type="text" id="from" name="minDate"
					value="${container.getMinDateString()}">
			</c:if>


			<label for="maxDate"><spring:message code="label.addtrips.to" /></label>
			<c:if test="${isMaxDate}">
				<input class="autosearch" type="text" id="to" name="maxDate">
			</c:if>
			<c:if test="${!isMaxDate}">
				<input class="autosearch" type="text" id="to" name="maxDate"
					value="${container.getMaxDateString()}">
			</c:if>
		<div style="float: right">
			<input type="submit" value="User search">
		</div>
	</form>
	<p>
	<p>
		<br>
		<br>
	<div id="result">
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
	</div>
	<hr />
	<script type="text/javascript">
		function formDatePicker() {
			$.datepicker.setDefaults($.datepicker.regional['${language}']);
			$("#from").datepicker({
				defaultDate : "+0w",
				changeMonth : true,
				numberOfMonths : 3,
				onClose : function(selectedDate) {
					$("#to").datepicker("option", "minDate", selectedDate);
				}
			});
			$("#to").datepicker({
				defaultDate : "+1w",
				changeMonth : true,
				numberOfMonths : 3,
				onClose : function(selectedDate) {
					$("#from").datepicker("option", "maxDate", selectedDate);
				}
			});

		}

		$(window).load(function() {
			formDatePicker();

		});
	</script>
	</script>
</section>
