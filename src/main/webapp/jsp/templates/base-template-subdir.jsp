
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value="/resources/css/layout.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/news.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/login.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/paging.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui.css" />">

<script src="../resources/js/jquery.min.js"></script>
<script src="../resources/js/jquery-ui.js"></script>
<script src="../resources/js/jquery.bootpag.js"></script>
<script src="../resources/js/initPaging.js"></script>
<script src="../resources/js/jquery.ui.datepicker-ua.js"></script>
<script src="../resources/js/jquery.ui.datepicker-es.js"></script>
<script src="../resources/js/fileUploader.js"></script>
<script>
	function onPagingEvent(event, num, resultsPerPage) {
		var elementSelectorForResult = "div#pagingcontent";
		$.ajax({
				async : false,
				beforeSend : function() {
							$(elementSelectorForResult).html(
				'<img id="ajaxLoadingImg" src="../resources/images/loading.gif">');
							},
				type : "POST",
				url : pageUrl,
				data : {
						pageNumber : num - 1,
						resultsPerPage : resultsPerPage
						}
			}).done(function(msg) {
					$(elementSelectorForResult).html(msg);
					});
	}

	function clone(obj) {
		return JSON.parse(JSON.stringify(obj));
	}

	jQuery.fn.ForceNumericOnly = function() {
		return this
				.each(function() {
					$(this)
							.keydown(
									function(e) {
										var key = e.charCode || e.keyCode || 0;
										return (key == 8 || key == 9
												|| key == 46
												|| (key >= 37 && key <= 40)
												|| (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
									});
				});
	};

	function serialize(object) {
		var strings = [];
		for ( var prop in object)
			if (object.hasOwnProperty(prop)) {
				strings.push(encodeURIComponent(prop) + "="
						+ encodeURIComponent(object[prop]));
			}
		return strings.join("&");
	}

	function ajaxLoader(domElement, targetPage, getData) {
		$
				.ajax(
						{
							async : false,
							beforeSend : function() {
								$(domElement)
										.html(
												'<img id="ajaxLoadingImg" src="../resources/images/loading.gif">');
							},
							type : "GET",
							url : targetPage,
							data : getData

						}).done(function(msg) {
					defaultGetData = clone(getData);
					$(domElement).html(msg);
				}

				);

	}
	

	$(document).ready(
			function() {
				if ($("div#imgUploadForm").length){
				fileFormLoader();
				uploadButtonEvent();
				}
				if ((typeof (defaultDomElement) != "undefined")
						&& (typeof (defaultTargetPage) != "undefined")
						&& (typeof (defaultGetData) != "undefined")) {
					ajaxLoader(defaultDomElement, defaultTargetPage,
							defaultGetData);
				}
				if (typeof pageUrl !== "undefined"){
				initPageWithPaging('${maxPageCount}', '${sizeOfPaging}',
						onPagingEvent, pageUrl);
				}
				
			});

</script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="navigation" />
	<div id="container">
 		<tiles:insertAttribute name="sidebar" />
		<tiles:insertAttribute name="content" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>
