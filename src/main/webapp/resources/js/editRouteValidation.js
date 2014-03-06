$(function editRoute() {
	$("#editRoutebtn0").click(function() {
		
	/*if(validate()) {
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
        
		 	}*/
    });
});


/*$(function getStationStartOnLineList() {
	$("#stationStart").autocomplete({
		source : function(request, response) {
			$.ajax({
				async : true,
				type : "GET",
				url : "getStationOnLineAutoCompleteList",
				dataType : "json",
				data : {
					stationStartsWith : request.term,
					lineName : $("#lineName").val()
				},
				success : function(data) {
					response($.map(data.stations, function(item) {
						return {
							label : item,
							value : item
						};
					}));
				}
			});
		},
		minLength : 1,
		open : function() {
			$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
		},
		close : function() {
			$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
		}
	});
});
$(function getStationStartOnLineList() {
	$("#stationEnd").autocomplete({
		source : function(request, response) {
			$.ajax({
				async : true,
				type : "GET",
				url : "getStationOnLineAutoCompleteList",
				dataType : "json",
				data : {
					stationStartsWith : request.term,
					lineName : $("#lineName").val()
				},
				success : function(data) {
					response($.map(data.stations, function(item) {
						return {
							label : item,
							value : item
						};
					}));
				}
			});
		},
		minLength : 1,
		open : function() {
			$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
		},
		close : function() {
			$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
		}
	});
});*/