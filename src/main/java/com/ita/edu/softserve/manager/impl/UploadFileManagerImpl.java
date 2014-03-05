package com.ita.edu.softserve.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ita.edu.softserve.manager.UploadFileManager;
import com.ita.edu.softserve.utils.UploadedFile;

@Service
public class UploadFileManagerImpl implements UploadFileManager{

	 @Autowired
	 UploadedFile uploadedFile;
	 
	 private MultipartFile file;
	 
	 @Override
	 public String  getFileName(UploadedFile uploadFile){
		 		 		  
		 MultipartFile file = uploadedFile.getFile(); 
		  		  	  
		  String fileName = file.getOriginalFilename();
		  return fileName;
	 }

	/**
	 * @return the file
	 */
	 @Override
	public MultipartFile getFile() {
		return file;
	}
	 
	 
}
