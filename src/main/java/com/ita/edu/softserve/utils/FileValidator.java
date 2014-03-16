package com.ita.edu.softserve.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component  
public class FileValidator implements Validator {  
  
@Autowired
UploadedFile file;

 @Override  
 public boolean supports(Class<?> arg0) {  
  // TODO Auto-generated method stub  
  return false;  
 }  
  
 @Override  
 public void validate(Object uploadedFile, Errors errors) {  
  
 file = (UploadedFile) uploadedFile;  
  
  if (file.getFile().getSize() == 0) {  
   errors.rejectValue("file", "uploadForm.selectFile",  
     "Please select a file!");  
  }  
  
 }


}  