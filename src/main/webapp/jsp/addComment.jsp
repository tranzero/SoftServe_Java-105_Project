<%@ page language="java" contentType="text/html; charset= UTF 8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<form method="post">
		<p>Enter your comment:</p>
		<textarea name="commentText" rows="15" cols="60" size="200"
			maxlength="200"></textarea>
		<br /> <input type="submit" value="Post comment"> <a
			href="javascript:history.back()"><input type="button"
			name="cancel" value="Cancel"></a>
	</form>
</section>