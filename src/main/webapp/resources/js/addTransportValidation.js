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
			genPrice : {
				required : true,
				minlength : 6,
				maxlength : 16,
			},
		},

	});
});
