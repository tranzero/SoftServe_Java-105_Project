<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%int i = 0;%>
<script type="text/javascript">
var pageUrl = "linesbystationresult";
</script>
<h1 >Lines Search</h1>


<div id = "content">
<form name="linesbystation" id = "linebystation" method="post">
		Enter station name : <input type="text" name="stationname" value="${stationName}" />
		<input class="button" type="submit" value="Find" />
	</form>
	<div id="pagingcontent"></div>
	<div id="pagecontent"></div>
	<div id="pagination"></div>	
</div>
	 

