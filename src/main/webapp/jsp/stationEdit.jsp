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
	<h1 style="text-align: center">Edit Station</h1>
	<hr />

	<hr>

	<form id="stationEdit" method="post" name="statEdit"
		style="text-align: center">
		<input id="stationId" type="hidden" name="stationId"
			value="${station.getStationId()}">

		<div>
			<p>StationCode:</p>
			<input id="stationCode" type="text" name="stationCode"
				value="${station.getStationCode()}">

			<p>StationName</p>
			<input id="stationName" type="text" name="stationName"
				value="${station.getStationName()}">

			<p>
				<input type="submit" value="Update Station"> 
				<input type="button" value="Cancel" onclick="window.location='/SoftServe_Java-105/stations';">
			</p>
			<br>

		</div>

	</form>
</div>
<!-- </body> -->
<!-- </html> -->