<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table class='table'>

			<thead>
				<tr>
					<th><spring:message code="label.users.userName" />

						<div style="float: right">
							<a id="usernameasc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a id="usernamedesc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><spring:message code="label.users.firstName" />
					
					<div style="float: right">
							<a id="firstnameasc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a id="firstnamedesc"
								href="javascript:void(0);">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div></th>
					<th><spring:message code="label.users.lastName" />
					<div style="float: right">
							<a id="lastnameasc"
								href="javascript:void(0);">
								<img alt="v" src="resources/images/downarrow.png">
							</a> <a id="lastnamedesc"
								href="javascript:void(0);">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.email" />
					<div style="float: right">
							<a id="emailasc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a id="emaildesc"
								href="javascript:void(0);">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.role" />
					<div style="float: right">
							<a id="roleasc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a id="roledesc"
								href="javascript:void(0);">
								<img alt="v" src="resources/images/uparrow.png">
							</a>
						</div>
					</th>
					<th><spring:message code="label.users.dateOfReg" />
					<div style="float: right">
							<a id="regdateasc"
								href="javascript:void(0);">
								<img alt="^" src="resources/images/downarrow.png">
							</a> <a id="regdatedesc"
								href="javascript:void(0);">
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
			<li class="prev"><a href="javascript:void(0);" id="firstpage">
					« </a></li>
			<li class="prev"><a href="javascript:void(0);" id="prevpage">
					<spring:message code="label.prev" />
			</a></li>
		</c:if>
		<c:if test="${pageNumber==1}">
			<li class="prev disabled"><a href="javascript:void(0);"> « </a></li>
			<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
						code="label.prev" />
			</a></li>
		</c:if>
		<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
			varStatus="status">
			<c:if test="${pageNumber!=i}">
				<li><a href="javascript:void(0);" id="page${i}"> ${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);" id="nextpage">
					<spring:message code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);" id="lastpage">
					» </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>

	<p>
	<p>
		<br>
		<spring:message code="label.trips.resultsperpage" />
		:
	<ul class="bootpag">
		<c:if test="${resultsPerPage!=10}">
			<li><a href="javascript:void(0);" id="resultsPerPage10">10</a></li>
		</c:if>
		<c:if test="${resultsPerPage==10}">
			<li class="disabled"><a href="javascript:void(0);">10</a></li>
		</c:if>
		<c:if test="${resultsPerPage!=20}">
			<li><a href="javascript:void(0);" id="resultsPerPage20">20</a></li>
		</c:if>
		<c:if test="${resultsPerPage==20}">
			<li class="disabled"><a href="javascript:void(0);">20</a></li>
		</c:if>
		<c:if test="${resultsPerPage!=50}">
			<li><a href="javascript:void(0);" id="resultsPerPage50">50</a></li>
		</c:if>
		<c:if test="${resultsPerPage==50}">
			<li class="disabled"><a href="javascript:void(0);">50</a></li>
		</c:if>
	</ul>

</div>

<script>
firstPageData = clone(defaultGetData);
firstPageData.pageNumber = 1;

$("a#firstpage").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, firstPageData);
});

prevPageData = clone(defaultGetData);
prevPageData.pageNumber = '${pageNumber-1}';

$("a#prevpage").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, prevPageData);
});

var pageElement = {};
for (var temp = '${firstPage}' * 1; temp <= '${lastPage}' * 1; temp = temp + 1) {
	pageElement[temp] = clone(defaultGetData);
	pageElement[temp].pageNumber = temp;
}

$.each(pageElement, function(index, value) {
	$("a#page" + index).click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, value);
	});
});

nextPageData = clone(defaultGetData);
nextPageData.pageNumber = '${pageNumber+1}';
$("a#nextpage").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, nextPageData);
});

lastPageData = clone(defaultGetData);
lastPageData.pageNumber = '${maxPages}';
$("a#lastpage").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, lastPageData);
});

resultPerPage10Data = clone(defaultGetData);
resultPerPage10Data.resultsPerPage = 10;
resultPerPage10Data.pageNumber = 1;
$("a#resultsPerPage10").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage10Data);
});

resultPerPage20Data = clone(defaultGetData);
resultPerPage20Data.resultsPerPage = 20;
resultPerPage20Data.pageNumber = 1;
$("a#resultsPerPage20").click(function() {
	ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage20Data);
});

	resultPerPage50Data = clone(defaultGetData);
	resultPerPage50Data.resultsPerPage = 50;
	resultPerPage50Data.pageNumber = 1;
	$("a#resultsPerPage50").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, resultPerPage50Data);
	});
	
	usernameascData = clone(defaultGetData);
	usernameascData.orderByParam = "u.userName";
	usernameascData.orderByDirection = "ASC";
	usernameascData.pageNumber = 1;
	$("a#usernameasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, usernameascData);
	});	
	
	usernamedescData = clone(defaultGetData);
	usernamedescData.orderByParam = "u.userName";
	usernamedescData.orderByDirection = "DESC";
	usernamedescData.pageNumber = 1;
	$("a#usernamedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, usernamedescData);
	});	
	
	firstnameascData = clone(defaultGetData);
	firstnameascData.orderByParam = "u.firstName";
	firstnameascData.orderByDirection = "ASC";
	firstnameascData.pageNumber = 1;
	$("a#firstnameasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, firstnameascData);
	});	
	
	firstnamedescData = clone(defaultGetData);
	firstnamedescData.orderByParam = "u.firstName";
	firstnamedescData.orderByDirection = "DESC";
	firstnamedescData.pageNumber = 1;
	$("a#firstnamedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, firstnamedescData);
	});	
	
	lastnameascData = clone(defaultGetData);
	lastnameascData.orderByParam = "u.lastName";
	lastnameascData.orderByDirection = "ASC";
	lastnameascData.pageNumber = 1;
	$("a#lastnameasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, lastnameascData);
	});	
	
	lastnamedescData = clone(defaultGetData);
	lastnamedescData.orderByParam = "u.lastName";
	lastnamedescData.orderByDirection = "DESC";
	lastnamedescData.pageNumber = 1;
	$("a#lastnamedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, lastnamedescData);
	});	
	
	emailascData = clone(defaultGetData);
	emailascData.orderByParam = "u.email";
	emailascData.orderByDirection = "ASC";
	emailascData.pageNumber = 1;
	$("a#emailasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, emailascData);
	});	
	
	emaildescData = clone(defaultGetData);
	emaildescData.orderByParam = "u.email";
	emaildescData.orderByDirection = "DESC";
	emaildescData.pageNumber = 1;
	$("a#emaildesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, emaildescData);
	});	
	
	roleascData = clone(defaultGetData);
	roleascData.orderByParam = "u.role";
	roleascData.orderByDirection = "ASC";
	roleascData.pageNumber = 1;
	$("a#roleasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, roleascData);
	});	
	
	roledescData = clone(defaultGetData);
	roledescData.orderByParam = "u.role";
	roledescData.orderByDirection = "DESC";
	roledescData.pageNumber = 1;
	$("a#roledesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, roledescData);
	});	
	
	regdateascData = clone(defaultGetData);
	regdateascData.orderByParam = "u.regDate";
	regdateascData.orderByDirection = "ASC";
	regdateascData.pageNumber = 1;
	$("a#regdateasc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, regdateascData);
	});	
	
	regdatedescData = clone(defaultGetData);
	regdatedescData.orderByParam = "u.regDate";
	regdatedescData.orderByDirection = "DESC";
	regdatedescData.pageNumber = 1;
	$("a#regdatedesc").click(function() {
		ajaxLoader(defaultDomElement, defaultTargetPage, regdatedescData);
	});	
	
	window.history.pushState(defaultGetData, document.title, location.protocol
			+ '//' + location.host + location.pathname + '?'
			+ serialize(defaultGetData)+'&lang=${language}');
	window.history.pathname = document.location.href;
</script>