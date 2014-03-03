<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	function toggle_visibility(id) {
		var e = document.getElementById(id);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';

		hideAllBut(id);
	}

	function hideAllBut(id) {
		var lists = document.querySelectorAll('.list');
		for (var i = lists.length; i--;) {
			if (lists[i].id != id) {
				lists[i].style.display = 'none';
			}
		}
	}
</script>
<section id="content">

	<form:form id = "addtobag" action= "/SoftServe_Java-105/addToBag/${tripId}/${seatType}" method="post" >
	
	 <table>
			<tr align=center>
				<td align=center>
					<p><input id="customerInfo" type="text" name="customerInfo"><br>
		
				</p>
				</td>
				</tr>
				</table>
		

		<div id="div1" class="list" style="display: none;">
			<p>

				<label><strong>${trip.getRemSeatClass1()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div2" class="list" style="display: none;">
			<p>
				<label><strong>${trip.getRemSeatClass2()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div3" class="list" style="display: none;">
			<p>
				<label><strong>${trip.getRemSeatClass3()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<br>
	

		<table>
			<tr align=center>
				<td align=center>
					<p>
						<label> Route name <strong>${trip.getTransport().getRoutes().getRouteName()}</strong></label></p>
						<br>
							<p>
						<label> Trip date <strong>${trip.getStartDate()}</strong></label></p>
						<br>
						<p>
						<label> Price <strong>${trip.getTransport().getGenPrice()}</strong></label></p>
						
		</table>
					
 		<input type="submit" value="Add to bag">
 	<!-- 	<input type="reset" value="Cancel"> -->
		<br>
	</form:form>

</section>
