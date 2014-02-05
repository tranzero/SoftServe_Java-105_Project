/*
 * Init jquery.bootpag.js paging function. Pagination will be bound to #pagination
 * element.
 * Controller serving paging needs has to start page numbering from 0.
 * Callback takes two params - event object and number of page.
 */
function initPaging(maxPageCount, sizeOfPaging, onChangePageFunc) {

	var initializationMap = {};

	initializationMap["total"] = maxPageCount;
	initializationMap["maxVisible"] = sizeOfPaging;

	$("#pagination").bootpag(initializationMap).on("page",
			function(event, num) {
				onChangePageFunc(event, num);
			});
}

function initPageWithPaging(maxPageCount, sizeOfPaging, onChangePageFunc) {
	initPaging(maxPageCount, sizeOfPaging, function(event, num) {
		onChangePageFunc(event, num, sizeOfPaging);
	});
	// First page is disabled so we have to make it enabled, use and disable it
	// again after the usage.
	$("li[data-lp='1']").removeClass('disabled');
	$("li[data-lp='1']").trigger("click");
	$("li[data-lp='1']").addClass('disabled');
};
function showOldestBlackListIpAddresses() {
	$(document)
			.ready(
					function() {
						$('div#resultsAndPaging')
								.html(
										'<img src="resources/images/loading.gif" style="width:100; height:28;">');
						$.ajax({
							type : "POST",
							url : "/mainpagepost",
							data : {
								pageNumber : "0",
								resultsPerPage : "10"
							}
						}).done(function(msg) {
							$("div#resultsAndPaging").html(msg);
						});
						$("div#resultsAndPaging")
								.on(
										'click',
										'#prevPageSubmit',
										function() {
											var prevPageNumber = $(
													"#prevPageNumberHidden")
													.val();
											var prevResultsPerPage = $(
													"#prevResultsPerPageHidden")
													.val();
											$('div#resultsAndPaging')
													.html(
															'<img src="resources/images/loading.gif" style="width:100; height:28;">');
											$
													.ajax(
															{
																type : "POST",
																url : "/mainpagepost",
																data : {
																	pageNumber : prevPageNumber,
																	resultsPerPage : prevResultsPerPage
																}
															})
													.done(
															function(msg) {
																$(
																		"div#resultsAndPaging")
																		.html(
																				msg);
															});
										});

						$("div#resultsAndPaging")
								.on(
										'click',
										'#nextPageSubmit',
										function() {
											var nextPageNumber = $(
													"#nextPageNumberHidden")
													.val();
											var nextResultsPerPage = $(
													"#nextResultsPerPageHidden")
													.val();
											$('div#resultsAndPaging')
													.html(
															'<img src="resources/images/loading.gif" style="width:100; height:28;">');
											$
													.ajax(
															{
																type : "POST",
																url : "/mainpagepost",
																data : {
																	pageNumber : nextPageNumber,
																	resultsPerPage : nextResultsPerPage
																}
															})
													.done(
															function(msg) {
																$(
																		"div#resultsAndPaging")
																		.html(
																				msg);
															});
										});
					});
}