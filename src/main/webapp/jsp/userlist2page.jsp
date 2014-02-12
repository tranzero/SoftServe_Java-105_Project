<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<c:if test="${!empty userList}">
	<div id="maxPageCount" style="display: none;">${maxPageCount}</div>
	<div id="resultsPerPage" style="display: none;">${resultsPerPage}</div>
	
	<table>
		
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

										

					

				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	
	
</c:if>
<c:if test="${empty userList}">
	<p>No results.</p>
</c:if>