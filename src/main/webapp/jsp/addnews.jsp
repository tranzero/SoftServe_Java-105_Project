<%@ page language="java" contentType="text/html; charset= UTF 8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id = "context">
	
	
	<form id="addnews" method="post" name="/addnews" action="addnews">
		<div>
		<p>Enter the Title of News that you want add News:</p>

		<input id="newsTitle" type="text" name="newsTitle" size="50" maxlength="100" value = "">
		<p>Enter the Description of News that you want add News:</p>

		<textarea name="newsDescription" id="newsDescription"></textarea>
		<p><input type="submit" value="ADD NEWS">    <a href='index'><input id="cancelnews" type="button" name="cancelnews" value="CANCEL"></a> </p>
		<br>
		</div>
	</form>

</div>