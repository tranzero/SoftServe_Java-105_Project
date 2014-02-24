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
	<h2>Choose Class-Seat</h2>
	<form:form action="reservationTicket" method="get">
		<table>
			<tr>
				<td><b>Choose Seat-Class you want : </b> <Br> <input
					type="radio" id="seat1" name="seat-class" value="seat1"
					onchange="toggle_visibility('div1');"> <strong>SeatClass
						№1</strong> <Br> <input type="radio" id="seat2" name="seat-class"
					value="seat2" onchange="toggle_visibility('div2');"> <strong>SeatClass
						№2</strong> <Br> <input type="radio" id="seat3" name="seat-class"
					value="seat3" onchange="toggle_visibility('div3');"> <strong>SeatClass
						№3</strong> <br /> <Br></td>
			</tr>
		</table>
		<div id="div1" class="list" style="display: none;">
			<p>

				<label><strong>(${trip.getRemSeatClass1()})</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div2" class="list" style="display: none;">
			<p>
				<label><strong>(${trip.getRemSeatClass2()})</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div3" class="list" style="display: none;">
			<p>
				<label><strong>(${trip.getRemSeatClass3()})</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<br>
		<a href="/SoftServe_Java-105/reservationTicketNext/${trip.getTripId()}">Make a booking</a>&nbsp
 		<input type="reset" value="Cancel">
		<br>
	</form:form>

</section>
