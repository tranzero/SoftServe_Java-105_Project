<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<h1>Remove Lines</h1>
	<form action="removeLines" method="post" name="remove">
		<select id="lineName" name="lineName">
			<c:forEach var="line" items="${linesList}">
				<option value= "${line.getLineName()}">${line.getLineName()}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Remove" form="remove">
	</form>
</div>
<!-- </body> -->
<!-- </html> -->