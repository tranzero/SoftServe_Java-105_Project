<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<link rel="stylesheet" href="resources/css/paging.css">
<script src="resources/js/jquery.bootpag.js"></script>
<script src="resources/js/initPaging.js"></script>
<script>
function onPagingEvent(event, num, resultsPerPage){
	var elementSelectorForResult = "div#newscontent";
	$.ajax({
		async : false,
		beforeSend : function(){$(elementSelectorForResult).html('<img id="ajaxLoadingImg" src="resources/images/loading.gif">');},
		type: "POST",
		url: "/mainpagepost",
		data: { pageNumber: num - 1, resultsPerPage: resultsPerPage }
		})
		.done(function( msg ) {
			$(elementSelectorForResult).html(msg);
	});
}

$(document).ready(function(){
	var maxPageCount = ${maxPageCount};
	var sizeOfPaging = ${sizeOfPaging};

	initPageWithPaging(maxPageCount, sizeOfPaging, onPagingEvent);
});
</script>
<div id = "content">
<div id="newscontent">
		<h1>
			News
		</h1>
		
	<div id="pagecontent"></div>
	<div id="pagination"></div>	
		
</div>
</div>