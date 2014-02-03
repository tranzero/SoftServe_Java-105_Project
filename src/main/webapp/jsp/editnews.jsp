<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id = "context">
	
	
	<form id="editnews" method="post" name="/editnews">
		<div>
		<input id="idTitle" type="hidden" name="idTitle" size="10" maxlength="10" value = "${post.getPostId()}">
		
		<p>Edit the Title of News that you want change News:</p>
		
		<input id="newsTitle" type="text" name="newsTitle" size="50" maxlength="100" value = "${post.getTitle()}">
		<p>Edit the Description of News that you want change News:</p>

		<textarea name="newsDescription" id="newsDescription">${post.getDescription()}</textarea>
		<p><input type="submit" value="Update NEWS"></p>
		<br>
		</div>
	</form>

</div>