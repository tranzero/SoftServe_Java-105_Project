<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<%int i = 0;%>
<h1 >Lines Search</h1>
<form:form name="linesbystation" id = "linebystation" method="post">
		Enter station name : <input type="text" name="stationname" />
		<input class="button" type="submit" name="submit" value="Find" />
	</form:form>
	 

