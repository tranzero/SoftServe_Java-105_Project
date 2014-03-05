<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript" src="resources/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.min.js"></script>

<title>Checkout</title>
<section id="content">

	<form method="post" id="myForm" name="myForm"
		action="/SoftServe_Java-105/bagPay">
		<p class="legend">
			<strong>Note:</strong> Required fields are marked with an asterisk (<em>*</em>)
		</p>
		<fieldset>
			<legend>[ Card Details ] </legend>
			<div>
				<label for="cartNumber">Credit Cart Number:<em>*</em></label> <input
					tabindex="1" size="40" type="text" name="cartNumber"
					id="cartNumber" />
			</div>
			<p>
			<div>
				<label for="firstName">Card holder First Name:<em>*</em></label> <input
					tabindex="2" size="40" type="text" name="firstName" id="firstName" />
			</div>
			<p>
			<div>
				<label for="lastName">Card holder Last Name:</label> <input
					tabindex="3" size="40" type="text" name="lastName" id="lastName" />
			</div>
			<p>
			<div>
				<label for="adress">Card holder billing adress<em>*</em></label> <input
					tabindex="4" size="40" type="text" name="adress" id="adress" />
			</div>
			<p>
			<div>
				<label for="securityCode">Security code:<em>*</em></label> <input
					type="password" tabindex="5" size="40" id="securityCode"
					name="securityCode">
			</div>
			<p>
		</fieldset>
		<div class="pad_top_bottom">
			<input type="submit" value="Pay" onclick="submitForm();" /> <input
				type="button" value="Cancel" onclick="window.history.back()" />
		</div>
	</form>

	<script>
		function submitForm() {
			var validator = $("#myForm")
					.validate(
							{
								rules : {

									/* cartNumber : {
										required : true,
										creditcard : true,
									}, */

									firstName : {
										required : true,
										minlength : 3,
										maxlength : 16,
									},

									lastName : {
										required : true,
										minlength : 3,
										maxlength : 16,
										digits : false,
									},

									adress : {
										required : true,
										minlength : 4,
										maxlength : 16,
									},

									securityCode : {
										required : true,
										minlength : 3,
										maxlength : 3,
										digits : true,
									},
								},
								errorElement : "span",
								messages : {

									cartNumber : {
										required : "This field is required.",
										creditcard : "Please enter a valid credit card number.",
									},

									firstName : {
										required : "This field is required.",
										maxlength : jQuery.validator
												.format("Please enter no more than {0} characters."),
										minlength : jQuery.validator
												.format("Please enter at least {0} characters."),
									},

									lastName : {
										required : "This field is required.",
										maxlength : jQuery.validator
												.format("Please enter no more than {0} characters."),
										minlength : jQuery.validator
												.format("Please enter at least {0} characters."),
									},

									adress : {
										required : "This field is required.",
										maxlength : jQuery.validator
												.format("Please enter no more than {0} characters."),
										minlength : jQuery.validator
												.format("Please enter at least {0} characters."),
									},

									securityCode : {
										required : "This field is required.",
										maxlength : jQuery.validator
												.format("This field have only {0} characters."),
										minlength : jQuery.validator
												.format("This field have only {0} characters."),
										digits : "Please enter only digits.",
									},

								}
							});
			if (validator.form()) { // validation perform
				$('form#myForm').attr({
					action : '/SoftServe_Java-105/bagPay'
				});
				$('form#myForm').submit();
			}
		}
	</script>

</section>