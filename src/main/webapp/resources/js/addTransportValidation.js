$(document).ready(function() {

	$("#addTransport.htm").validate({

		rules : {

			transportCode : {
				required : true,
				minlength : 4,
				maxlength : 16,
				digits : true,
			},

			startTime : {
				required : true,
				minlength : 8,
				maxlength : 8,
			},

			seatclass1 : {
				required : true,
				minlength : 6,
				maxlength : 16,
			},

			seatclass2 : {
				required : true,
				minlength : 6,
				maxlength : 16,
			},

			seatclass3 : {
				required : true,
				minlength : 6,
				maxlength : 16,
			},
		},

		messages : {

			transportCode : {
				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 18 characters",
				digits : "This field must have digits",
			},

			startTime : {
				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},

			seatclass1 : {

				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},

			seatclass2 : {

				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},

			seatclass3 : {

				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},
		}
	});
});
