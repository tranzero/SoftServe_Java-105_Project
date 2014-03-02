<%@ page language="java" contentType="text/html; charset= UTF 8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<form:form method="post" modelAttribute="responses">
		<p><spring:message code="label.responses.enterComment" /></p>
		<p><form:errors path="comment" cssClass="error" /></p>
		<form:textarea path="comment" name="commentText" rows="15" cols="60"
			size="200" maxlength="200"></form:textarea>
		<br />
		<input type="submit" value="<spring:message code="label.responses.postComment" />">
		<a href="javascript:history.back()"><input type="button"
			name="cancel" value="<spring:message code="label.cancel" />"></a>
	</form:form>
</section>