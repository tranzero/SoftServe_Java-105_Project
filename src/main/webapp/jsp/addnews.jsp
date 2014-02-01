<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id = "context">
	<form id="addnews" method="post" name="addnews">
		<div>
		<p>Enter the Title of News that you want add News:</p>

		<input id="newsTitle" type="text" name="newsTitle" size="20" maxlength="50">
		<p>Enter the Description of News that you want add News:</p>

		<textarea name="newsDescription" id="newsDescription" required form="text"></textarea>
		<p><input type="submit" value="ADD NEWS" form="text"></p>
		<br>
		</div>
	</form>
</div>