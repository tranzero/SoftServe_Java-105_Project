$(function addRoute() {
	$("#addRoutebtn").click(function() {
	if(validate()) {
        $.ajax(
				{
					type : "POST",
					url : "addRoutePage",
					dataType : "json",
					data : {
						routeCode : $("#routeCode").val(),
						lineName : $("#lineName").val(),
						stationStart : $("#stationStart").val(),
						stationEnd : $("#stationEnd").val()
					},
					success : function(json) {
						if(json[0]=="0"){
							window.location = '/SoftServe_Java-105/routesAllEdit';
						}
						else{
							parseJson(json);
						}
					},
	                fail: function() {
	                    alert('there was some error');
	                },
				});
        
		 	}
    });
});

$(function validateRouteCode() {
	$("#routeCode").autocomplete({
		source : function(request) {
		
			$.ajax({
				async : true,
				type : "GET",
				url : "findRouteCodeInDB",
				dataType : "json",
				data : {
					routeCode: request.term
				},
				success : function(json) {
					$("#routeCodeError").text(json[1]);
					defineStyle(json);
				}
			});
		},
		minLength : 5
	});
});


function parseJson(json) {
	$("#routeCodeError").text(json[2]);
	$("#lineNameError").text(json[4]);
	$("#stationStartError").text(json[6]);
	$("#stationEndError").text(json[8]);

	if(json[1]==0){
		if ($("#routeCodeError").hasClass("error")) {
            $("#routeCodeError").removeClass('error');
            $("#routeCodeError").addClass('success');
        } else {
            if (!$("#routeCodeError").hasClass("success")) {
                $("#routeCodeError").addClass('success');
            }
        }
	}
	if(json[1]==1){
		if ($("#routeCodeError").hasClass('success')) {
            $("#routeCodeError").removeClass('success');
            $("#routeCodeError").addClass('error');
        } else {
            if (!$("#routeCodeError").hasClass('error')) {
                $("#routeCodeError").addClass('error');
            }
        }
	}
};

function  validate() {
	var isValid= true;
	
	if ($("#routeCodeError").hasClass("error")){
		isValid= false;
	}
	
	return isValid;
}

function defineStyle(json) {
	if(json[0]==0){
		if ($("#routeCodeError").hasClass("error")) {
            $("#routeCodeError").removeClass('error');
            $("#routeCodeError").addClass('success');
        } else {
            if (!$("#routeCodeError").hasClass("success")) {
                $("#routeCodeError").addClass('success');
            }
        }
	}
	if(json[0]==1){
		if ($("#routeCodeError").hasClass('success')) {
            $("#routeCodeError").removeClass('success');
            $("#routeCodeError").addClass('error');
        } else {
            if (!$("#routeCodeError").hasClass('error')) {
                $("#routeCodeError").addClass('error');
            }
        }
	}
};

