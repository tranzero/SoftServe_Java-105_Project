<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<script type="text/javascript">
// $(function () {
// 	var elementSelectorForImgName = "input#newsImg";
//     $('input#uploadFileButton').on('click', function () {
        
//         $.ajax({
//         	async : false,
//     		type: "POST",
//             url: 'fileUpload',
//             data: {
//             	uploadedFile: $("input[name=file]").val()
               
//             }
//         }).done(function( msg ) {
// 			$(elementSelectorForImgName).html(msg);
//     });
// });
// });
</script>

 
  <form id="uploadedFile" method="post"  enctype="multipart/form-data" 
   name="uploadedFile" action="fileUpload">  
     <p>Upload File: </p>  
     <p><input id="uploadFileInput" type="file" name="file" /> </p>  
     <p><input id="uploadFileButton" type="submit" value="Upload" /></p>  
  </form>  
