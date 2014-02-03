<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<div id="context">
	<h1>Add New Station</h1>
	<hr />

	<hr>

	<form id="addstation" method="post" name="addstation">
		<div>
			<p>StationCode:</p>
			<input id="stationCode" type="text" name="stationCode" size="20"
				maxlength="100" value="">

			<p>StationName:</p>
			<input id="stationName" type="text" name="stationName" size="20"
				maxlength="100" value=""> <br>
			<p>
				<input type="submit" value="ADD NEW STATION" form="text">
			</p>
		</div>
	</form>
</div>
<!-- </body> -->
<!-- </html> -->