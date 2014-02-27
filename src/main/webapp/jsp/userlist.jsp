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

<script>
var defaultDomElement = "div#result"; 
var defaultTargetPage = "userListPage"; 
var defaultGetData={
		pageNumber: "${pageNumber}",
		resultsPerPage: "${resultsPerPage}",
		searchstring: "${container.getSearchString()}",
		isAdmin: "${container.getIsAdmin()}",
		isRegUser: "${container.getIsRegUser()}",
		isManager: "${container.getIsManager()}",
		minDate: "${container.getMinDateString()}",
		maxDate: "${container.getMaxDateString()}",
		orderByParam: "${container.getOrderByParam()}",
		orderByDirection :"${container.getOrderByDirection()}"
};

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
				<input type="checkbox" name="isAdmin" id="isAdmin" value="true"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsAdmin()}">
				<input type="checkbox" name="isAdmin" id="isAdmin" value="true"
					class="autosearch">
			</c:if>

			Manager:
			<c:if test="${container.getIsManager()}">
				<input type="checkbox" name="isManager" id="isManager" value="true"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsManager()}">
				<input type="checkbox" name="isManager" id="isManager" value="true"
					class="autosearch">
			</c:if>
			Registered user:
			<c:if test="${container.getIsRegUser()}">
				<input type="checkbox" name="isRegUser" id="isRegUser" value="true"
					class="autosearch" checked>
			</c:if>
			<c:if test="${!container.getIsRegUser()}">
				<input type="checkbox" name="isRegUser" id="isRegUser" value="true"
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
		<br> <br>
	<div id="result">
		<table class='table'>

			<thead>
				<tr>
					<th><spring:message code="label.users.userName" />

						<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.userName&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.userName&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><spring:message code="label.users.firstName" />
					
					<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.firstName&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.firstName&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><spring:message code="label.users.lastName" />
					<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.lastName&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.lastName&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.email" />
					<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.email&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.email&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.role" />
					<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.role&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.role&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.dateOfReg" />
					<div style="float: right">
							<a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.regDate&orderByDirection=ASC">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a
								href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=u.regDate&orderByDirection=DESC">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
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
		<div class="pagination">
			<ul class="bootpag">
				<c:if test="${pageNumber>1}">
					<li class="prev"><a
						href="?pageNumber=1&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							« </a></li>
					<li class="prev"><a
						href="?pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							<spring:message code="label.prev" />
					</a></li>
				</c:if>
				<c:if test="${pageNumber==1}">
					<li class="prev disabled"><a href="javascript:void(0);"> «
					</a></li>
					<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
								code="label.prev" />
					</a></li>
				</c:if>
				<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
					varStatus="status">
					<c:if test="${pageNumber!=i}">
						<li><a
							href="?pageNumber=${i}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
								${i} </a></li>
					</c:if>
					<c:if test="${pageNumber==i}">
						<li class="disabled"><a href="javascript:void(0);"> ${i}
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNumber<maxPages}">
					<li class="next"><a
						href="??pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							<spring:message code="label.next" />
					</a></li>
					<li class="next"><a
						href="?pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							» </a></li>
				</c:if>
				<c:if test="${pageNumber==maxPages}">
					<li class="next disabled"><a href="javascript:void(0);"> <spring:message
								code="label.next" />
					</a></li>
					<li class="next disabled"><a href="javascript:void(0);"> »
					</a></li>
				</c:if>
			</ul>
			<p>
			<p>
				<br>
				<spring:message code="label.trips.resultsperpage" />
				:
			<ul class="bootpag">
				<c:if test="${resultsPerPage!=10}">
					<li><a
						href="?pageNumber=$1&resultsPerPage=10&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							10</a></li>
				</c:if>
				<c:if test="${resultsPerPage==10}">
					<li class="disabled"><a href="javascript:void(0);">10</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=20}">
					<li><a
						href="?pageNumber=1&resultsPerPage=20&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							20</a></li>
				</c:if>
				<c:if test="${resultsPerPage==20}">
					<li class="disabled"><a href="javascript:void(0);">20</a></li>
				</c:if>
				<c:if test="${resultsPerPage!=50}">
					<li><a
						href="?pageNumber=1&resultsPerPage=50&searchstring=${encoder.encode(
						container.getSearchString())}&isAdmin=${
						container.getIsAdmin()}&isManager=${
						container.getIsManager()}&isRegUser=${
						container.getIsRegUser()}&minDate=${encoder.encode(
						container.getMinDateString())}&maxDate=${encoder.encode(
						container.getMaxDateString())}&orderByParam=${encoder.encode(
						container.getOrderByParam())}&orderByDirection=${encoder.encode(
						container.getOrderByDirection())}">
							50</a></li>
				</c:if>
				<c:if test="${resultsPerPage==50}">
					<li class="disabled"><a href="javascript:void(0);">50</a></li>
				</c:if>
			</ul>
		</div>
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
			setInterval(function(){
 				var curVal;
 				var prevVal;
 			    curVal = $(".autosearch").serialize();
 			    prevVal  = $(".autosearch").data("prevVal") || null;
 			    $(".autosearch").data("prevVal",curVal);
 			    if (prevVal !== curVal) {
 			    	var searchData={
 			    			pageNumber: 1,
 			    			resultsPerPage: "${resultsPerPage}",
 			    			searchstring: $("input#searchstring").val(), 
 			    			isAdmin: $("input#isAdmin").is(':checked'),
 			    			isRegUser: $("input#isRegUser").is(':checked'),
 			    			isManager: $("input#isManager").is(':checked'),
 			    			minDate: $("input#from").val(),
 			    			maxDate: $("input#to").val(),
 			    			orderByParam: "${container.getOrderByParam()}",
 			    			orderByDirection :"${container.getOrderByDirection()}"
 			    	};
 			    	ajaxLoader(defaultDomElement, defaultTargetPage, searchData);
 			    }
 			}, 3000);
		});
	</script>
	</script>
</section>
