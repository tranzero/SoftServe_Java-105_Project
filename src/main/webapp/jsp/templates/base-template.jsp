 <%@page contentType = "text/html" pageEncoding = "UTF-8" %> 
 <%@ taglib prefix= "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
<title>
<tiles:getAsString name="title"/>
</title>
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet" type="text/css" />

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/css/paging.css">
<script src="resources/js/jquery.bootpag.js"></script>
<script src="resources/js/initPaging.js"></script>
<script>
function onPagingEvent(event, num, resultsPerPage){
	var elementSelectorForResult = "div#pagingcontent";
	$.ajax({
		async : false,
		beforeSend : function(){$(elementSelectorForResult).html('<img id="ajaxLoadingImg" src="resources/images/loading.gif">');},
		type: "POST",
		url: pageUrl,
		data: { pageNumber: num - 1, resultsPerPage: resultsPerPage }
		})
		.done(function( msg ) {
			$(elementSelectorForResult).html(msg);
	});
}
$(document).ready(function(){
	initPageWithPaging('${maxPageCount}', '${sizeOfPaging}', onPagingEvent, pageUrl);
});
</script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="navigation"/>
	<tiles:insertAttribute name="sidebar"/>
	<tiles:insertAttribute name="content"/>
	<tiles:insertAttribute name="footer"/>
<%--
	<div class="headerStyle">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="navigationStyle"> <tiles:insertAttribute name="navigation"/> </div>
<div class="contentStyle"> <tiles:insertAttribute name="content"/> </div>
<div class=footerStyle> <tiles:insertAttribute name="footer"/> </div>
--%>
</body>
</html>
