$(function getLineList() {
	$("#lineName").autocomplete({
		source : function(request, response) {
			$.ajax({
				async : true,
				type : "GET",
				url : "getLineAutoCompleteList",
				dataType : "json",
				data : {
					lineStartsWith : request.term
				},
				success : function(data) {
					response($.map(data.lines, function(item) {
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
});