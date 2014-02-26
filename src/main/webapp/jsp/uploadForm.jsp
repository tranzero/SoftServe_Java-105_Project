<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  

 
  <form id="uploadedFile" method="post"  enctype="multipart/form-data" 
   name="uploadedFile" action="fileUpload">  
     <p>Upload File: </p>  
     <p><input type="file" name="file" /> </p>  
     <p><input type="submit" value="Upload" /></p>  
  </form>  
