$(document).ready(function() {

	$("#userEdit").validate({
		rules : {

			userFirstName : {
				required : true,
				minlength : 2,
				maxlength : 18,
			},

			lastName : {
				required : true,
				minlength : 2,
				maxlength : 38,
			},

			email : {
				required : true,
				minlength : 2,
				maxlength : 38,
				
			},

			parole : {
				required : true,
				minlength : 2,
				maxlength : 38,
				digits : true,
			},

		},

		messages : {

			userFirstName : {
				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 18 characters",
			},
			
			lastName : {
				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},

			email : {

				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
			},
			
			parole : {

				required : "This field is required",
				minlength : "This field must have at least 2 characters",
				maxlength : "This field should be a maximum of 38 characters",
				digits : "This field must have digits",
			},

		}

	});

});