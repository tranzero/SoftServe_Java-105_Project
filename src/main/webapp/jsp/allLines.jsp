<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
</head>
<body>
	<%
		int i = 0;
	%>
	<table>
		<tr>
			<td>Number</td>
			<td>Line Name</td>

		</tr>
		<c:forEach var="line" items="${linesList}">
			<tr>
				<td><%=++i%></td>
				<td>${line.getLineName()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>