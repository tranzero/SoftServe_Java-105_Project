<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section id="content">
	<h2 align="center">
		<spring:message code="label.users.userEdit" />
	</h2>

	<form:form method="post" action="userEdit2.htm" commandName="user">

		<p>
			<form:hidden path="userId" />
		<p>
			<%-- <p>
			<spring:message code="label.users.userName" />
		</p> --%>

			<%-- 	<p>
			<form:input type="text" path="userName" onfocus="checkpostal()" />
			<form:errors path="userName" cssClass="error" />
		</p> --%>


			<spring:message code="label.users.firstName" />
		</p>


		<p>
			<form:input type="text" path="firstName" onfocus="checkpostal()" />
			<form:errors path="firstName" cssClass="error" />
		</p>

		<p>
			<spring:message code="label.users.lastName" />
		</p>

		<p>
			<form:input type="text" path="lastName" onfocus="checkpostal()" />
			<form:errors path="lastName" cssClass="error" />
		</p>

		<p>
			<spring:message code="label.users.email" />
		</p>

		<p>
			<form:input type="text" path="email" onfocus="checkpostal()" />
			<form:errors path="email" cssClass="error" />
		</p>

		<p>
			<spring:message code="label.users.password" />
		</p>

		<p>
			<form:input type="password" path="password" onfocus="checkpostal()" />
			<form:errors path="password" cssClass="error" />
		</p>

		<p>
			<spring:message code="label.users.role" />
			:
		<p>
			<form:input type="text" path="role" onfocus="checkpostal()" />
			<form:errors path="role" cssClass="error" />
		</p>

		<p>
			<input name="control" type="submit"
				value="<spring:message code ="label.update" />" /> 
				
				<input
				type="button" value="<spring:message code="label.cancel"/>"
				onclick="window.location='/SoftServe_Java-105/userlist2';">
		</p>

	</form:form>


	<script src="resources/js/jquery.js"></script>

</section>



