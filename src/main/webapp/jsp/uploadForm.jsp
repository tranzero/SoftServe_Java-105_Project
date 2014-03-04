<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
  <form id="uploadedFile" method="post"  enctype="multipart/form-data" 
   name="uploadedFile" action="fileUpload">  
     <p>Upload File: </p>  
     <p><input id="uploadFileInput" type="file" name="file" /> </p>  
     <p><input id="uploadFileButton" type="button" value="Upload" onClick="savepic()" /></p>  
  </form>  
