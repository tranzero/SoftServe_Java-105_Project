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
</head>
<body>
	<div class="headerStyle">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="navigationStyle"> <tiles:insertAttribute name="navigation"/> </div>
<div class="contentStyle"> <tiles:insertAttribute name="content"/> </div>
<div class=footerStyle> <tiles:insertAttribute name="footer"/> </div>
</body>
</html>