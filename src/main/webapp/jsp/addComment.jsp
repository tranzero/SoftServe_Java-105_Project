<%@ page language="java" contentType="text/html; charset= UTF 8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<form:form method="post" modelAttribute="responses">
		<p>Enter your comment:</p>
		<form:textarea path="comment" name="commentText" rows="15" cols="60"
			size="200" maxlength="200"></form:textarea>
		<form:errors path="comment" cssClass="error" />
		<br />
		<input type="submit" value="Post comment">
		<a href="javascript:history.back()"><input type="button"
			name="cancel" value="Cancel"></a>
	</form:form>
</section>