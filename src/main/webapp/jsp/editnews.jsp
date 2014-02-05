<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="content">
	
	
	<form id="editnews" method="post" name="/editnews">
		<div>
		<input id="idTitle" type="hidden" name="idTitle" size="10" maxlength="10" value = "${post.getPostId()}">
		
		<p>Edit the Title of News that you want change News:</p>
		
		<input id="newsTitle" type="text" name="newsTitle" size="50" maxlength="100" value = "${post.getTitle()}">
		<p>Edit the Description of News that you want change News:</p>

		<textarea name="newsDescription" id="newsDescription">${post.getDescription()}</textarea>
		<p><input type="submit" value="Update NEWS"> <a href='managenews'><input id="cancelnews" type="button" name="cancelnews" value="CANCEL"></a></p>
		<br>
		</div>
	</form>

</section>