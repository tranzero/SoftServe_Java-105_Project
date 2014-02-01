<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id = "context">
	
	
	<form id="addnews" method="post" name="addnews">
		<div>
		<p>Edit the Title of News that you want change News:</p>
		${post.getTitle()}
		<input id="newsTitle" type="text" name="newsTitle" size="20" maxlength="100" value = "${post.getTitle()}">
		<p>Edit the Description of News that you want change News:</p>

		<textarea name="newsDescription" id="newsDescription" required form="text">${post.getDescription()}</textarea>
		<p><input type="submit" value="Update NEWS" form="text"></p>
		<br>
		</div>
	</form>

</div>