$(function editRoute() {
	$("#editRoutebtn").click(function() {
        $.ajax(
				{
					type : "POST",
					url : "editRoutePage",
					dataType : "json",
					data : {
						routeId : $("#routeId").val(),
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
