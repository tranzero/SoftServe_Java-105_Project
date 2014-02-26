<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section id="content">
	<h2 align="center">
		<spring:message code="label.users.userEdit" />
	</h2>
	
	<form:form method="post" action="userEdit.htm" commandName="user">
	
	<p><form:hidden path="userId" />
	
	<p><spring:message code="label.users.firstName"/></p>
			<p><form:input type="text" path="firstName" onfocus="checkpostal()" />
			<form:errors path="firstName" cssClass="error" /></p>		
						
			<p><spring:message code="label.users.lastName"/></p>
			<p><form:input type="text" path="lastName" onfocus="checkpostal()" />
			<form:errors path="lastName" cssClass="error" /></p>
			
			<p><spring:message code="label.users.email"/></p>
			<p><form:input type="text" path="email" onfocus="checkpostal()" />
			<form:errors path="email" cssClass="error" /></p>
			
			<p><spring:message code="label.users.password"/></p>
			<p><form:input type="text" path="password" onfocus="checkpostal()" />
			<form:errors path="password" cssClass="error" /></p>
			
			<p>
				<spring:message code="label.users.role" />	:
				<p><form:input type="text" path="role" onfocus="checkpostal()" />
				<form:errors path="role" cssClass="error" /></p>
				<%-- <form:select id="role" name="role" path="user.role">
					<form:options itemLabel="description" />
				</form:select> 
			</p>--%>
			
			
			<p><input name="control" type="submit" value="Update" />
			
			<input type="button" value="<spring:message code="label.stations.cancel"/>" 
				onclick="window.location='/SoftServe_Java-105/userlist';"></p>
			
	
	
	</form:form>

	<script src="resources/js/jquery.js"></script>






</section>




<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ita.edu.softserve.entity.*"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="resources/js/usersEdit-validation.js"></script>

<jsp:directive.page import="java.util.*" />

<section id="content">
	<h2 align="center">
		<spring:message code="label.users.userEdit" />
	</h2>
	<hr />

	<form id="userEdit" method="post" action="" name="/userEdit"
		style="text-align: left">

		<input id="userId" type="hidden" name="userId"
			value="${user.getUserId()}">

		<div>
			<p>
				<spring:message code="label.users.firstName" />
				: <input id="userFirstName" type="text" name="userFirstName"
					value="${user.getFirstName()}">
			</p>

			<p>
				<spring:message code="label.users.lastName" />
				: <input id="lastName" type="text" name="lastName"
					value="${user.getLastName()}"><br>
			</p>

			<p>
				<spring:message code="label.users.email" />
				: <input id="email" type="text" name="email"
					value="${user.getEmail()}">
			</p>



			<p>
				<spring:message code="label.users.password" />
				: <input id="password" type="password" name="password"
					value="${user.getPassword()}">
			</p>

			<p>
				<spring:message code="label.users.role" />
				:

				<form:select id="role" name="role" path="user.role">
					<form:options itemLabel="description" />
				</form:select>
			</p>

			<br>

			<p align="center">
				<input type="submit"
					value="<spring:message
					code="label.update" />"> <input
					type="button" value="<spring:message
					code="label.cancel" />"
					onclick="window.location='/SoftServe_Java-105/userlist2';">
			</p>

			<br>

		</div>

	</form>
</section>
 --%>