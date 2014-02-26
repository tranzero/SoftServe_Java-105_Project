package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.ita.edu.softserve.utils.UploadedFile;

@Service
public interface UploadFileManager {

	

	String getFileName(UploadedFile uploadFile);

	MultipartFile getFile();
	

}
