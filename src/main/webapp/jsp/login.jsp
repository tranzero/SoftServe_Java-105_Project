<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="content">
	<c:if test='${not empty param.error}'>
		<font color='red'> <spring:message
				code="label.Login.loginerror" /><br /> <spring:message
				code="label.login.reason" />${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
		</font>
	</c:if>
	<form id="loginform" name='loginForm'
		action="<c:url value="/j_spring_security_check"/>" method='POST'>
		<div class="control-group">
			<label for="username"><spring:message
					code="label.login.username" /></label> <input type="text" id="username"
				placeholder="Username" name='j_username' value='' />
		</div>
		<div class="control-group">
			<label for="password"><spring:message
					code="label.login.password" /></label> <input type="password"
				id="password" placeholder="Password" name='j_password' />
		</div>
		<div class="control-group">
			<div class="center">
				<input id="rememberme" type="checkbox"
					name="_spring_security_remember_me" />
				<spring:message code="label.login.remember" />
			</div>
		</div>
		<div class="control-group">
			<div class="center">
				<input type="submit" name="submit" value="Login" id="lsubmit" /> <a
					href='mainpage'><input type="button" id="lcancel" name="cancel"
					value="Cancel"></a>
			</div>
		</div>
	</form>
</section>