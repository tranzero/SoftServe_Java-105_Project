package com.ita.edu.softserve.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ita.edu.softserve.manager.PropertiesManager;
import com.ita.edu.softserve.utils.FileValidator;
import com.ita.edu.softserve.utils.UploadedFile;

@Controller
public class UploadController {

	@Autowired
	FileValidator fileValidator;

	@Autowired
	UploadedFile uploadedFile;
	
	@Autowired
	PropertiesManager propertyManager;

	@RequestMapping(value ="/fileUploadForm", method=RequestMethod.POST)
	public String getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		return "uploadForm";
	}

	@RequestMapping("/fileUpload")
	public String fileUploaded(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result, Map<String, String> modelMap) {
		
		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String fileName = file.getOriginalFilename();
		modelMap.put("filename", fileName);
		if (result.hasErrors()) {
			return "addnews";
		}

		try {
			inputStream = file.getInputStream();

			File newFile = new File(propertyManager.getImgPath() + fileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "addnews";
	}

}