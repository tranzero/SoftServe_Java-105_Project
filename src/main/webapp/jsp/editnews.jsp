<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<section id="content">
	
	
	<form id="editnews" method="post" name="/editnews">
		<div>
		<input id="idTitle" type="hidden" name="idTitle" size="10" maxlength="10" value = "${post.getPostId()}">
		
		<p><spring:message code="label.navigation.editNews.title"/></p>
		
		<input id="newsTitle" type="text" name="newsTitle" size="50" maxlength="100" value = "${post.getTitle()}">
		<p><spring:message code="label.navigation.editNews.description"/></p>

		<textarea name="newsDescription" id="newsDescription">${post.getDescription()}</textarea>
		<p><input type="submit" value="<spring:message code="label.updater"/>"> <a href='managenews'><input id="cancelnews" type="button" name="cancelnews" value="<spring:message code="label.cancel"/>"></a></p>
		<br>
		</div>
	</form>

</section>