<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
  <form id="uploadedFile" method="post"  enctype="multipart/form-data" 
   name="uploadedFile" action="fileUpload">  
     <p><spring:message code="label.uploadmsg"/></p>  
     <p><input id="uploadFileInput" type="file" name="file" /> </p>  
     <p><input id="uploadFileButton" type="button" value="Upload" /></p>  
  </form>  
