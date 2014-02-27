<%@ page language="java" contentType="text/html; charset= UTF 8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
$(window).load(function() {
	var elementSelectorForResult = "div#imgUploadForm";
	$.ajax({
		async : false,
		type: "POST",
		url: "fileUploadForm"
		}).done(function( msg ) {
			$(elementSelectorForResult).html(msg);
	});
});

</script>
<section id="content">
<div id="imgUploadForm"></div>	
	
	<form id="addnews" method="post" name="/addnews" action="addnews">
		<div>
		<p>Image name</p>
		<input id="fileName" name="fileName" type="text" value="${filename}" size="50" maxlength="100">
		
		<p><spring:message code="label.navigation.addNews.title"/></p>
		
		<input id="newsTitle" type="text" name="newsTitle" size="50" maxlength="100" value = "">
		<p><spring:message code="label.navigation.addNews.description"/></p>

		<textarea name="newsDescription" id="newsDescription"></textarea>
		<p><input type="submit" value="<spring:message code="label.navigation.addNews"/>">
		 <a href='managenews'><input id="cancelnews" type="button" name="cancelnews" value="<spring:message code="label.stations.cancel"/>"></a> </p>
		<br>
		</div>
	</form>

</section>