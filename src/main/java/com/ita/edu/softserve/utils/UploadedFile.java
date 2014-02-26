package com.ita.edu.softserve.utils;


  
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;  
 
@Component
public class UploadedFile {  
  
 private MultipartFile file;  
  
 public MultipartFile getFile() {  
  return file;  
 }  
  
 public void setFile(MultipartFile file) {  
  this.file = file;  
 }  
}  