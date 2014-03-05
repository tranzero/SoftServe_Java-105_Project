function fileFormLoader(){
	var elementSelectorForResult = "div#imgUploadForm";
	$.ajax({
		async : false,
		beforeSend : function() {
			$(elementSelectorForResult).html(
'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
			},
		type: "POST",
		url: "fileUploadForm"
		}).done(function( msg ) {
			$(elementSelectorForResult).html(msg);
	});
	}


function uploadButtonEvent(){
	 $("#uploadFileButton").on("click", function(){

		var elementSelectorForResult = "div#fileNameInput";
		var uploadFile = new FormData($("form#uploadedFile")[0]);

		$.ajax({
			beforeSend : function() {
				$(elementSelectorForResult).html(
	'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
				},
			cache: false,
			contentType: false,
			processData: false, 			
			async : false,
			type: "POST",
			url: "fileUpload",
			data: uploadFile
			}).done(function(msg) {
				$(elementSelectorForResult).html(msg);
		});
  	});
}