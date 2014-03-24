$(function() {
	$("#timeMin").datetimepicker({
		datepicker : false,
		format : "H:i:s",
		step : 5,
		onShow : function(ct) {
			this.setOptions({
				maxTime : $("#timeMax").val() ? $("#timeMax").val() : false
			});
		}
	});
});

$(function() {
	$("#timeMax").datetimepicker({
		datepicker : false,
		format : "H:i:s",
		step : 5,
		onShow : function(ct) {
			this.setOptions({
				minTime : $("#timeMin").val() ? $("#timeMin").val() : false
			});
		}
	});
});

$(function getStationList() {
	$("#stationList").autocomplete({
		source : function(request, response) {
			$.ajax({
				async : true,
				type : "GET",
				url : "getStationAutoCompleteList",
				dataType : "json",
				data : {
					stationStartsWith : request.term
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