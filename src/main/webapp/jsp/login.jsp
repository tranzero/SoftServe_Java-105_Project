<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<section id="content">
	<form id="loginform" name='loginForm'
		action="<c:url value="/j_spring_security_check"/>" method='POST'>
		<h1><spring:message code="label.header.login"/></h1>
		<span>
			<c:if test='${not empty param.error}'>
				<font color='red' size="2"> <spring:message
					code="label.Login.loginerror" /> <spring:message
					code="label.login.reason" />
				</font>
			</c:if>
		</span>
		 <div> 			
			<input type="text" id="username"
				placeholder="<spring:message code="label.login.username" />" name='j_username' value='' />
		</div>
		<div>
			<input type="password"
				id="password" placeholder="<spring:message code="label.login.password" />" name='j_password' />
		</div>
		<div id="rememberme">
			<input type="checkbox"
				name="_spring_security_remember_me" />
			<spring:message code="label.login.remember" />
		</div>
		<div class="clear">
			<input type="submit" name="submit" value="<spring:message code="label.header.login"/>" id="lsubmit" /> <a
				href='mainpage'>
				<input type="button" id="lcancel" name="cancel"
				value="<spring:message code="label.cancel"/>"></a>
		</div>
	</form>
</section>