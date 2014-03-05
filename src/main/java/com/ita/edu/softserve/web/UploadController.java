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
	
	private static final String FILE_UPLOAD_FORM = "fileUploadForm";
	private static final String FILE_UPLOAD = "fileUpload";
	private static final String MODEL_ATTRIBUTE_UPLOADED_FILE = "uploadedFile";
	private static final String FILE_NAME_MAP_KEY = "filename";
	
	private String uploadFormView = "uploadForm";
	private String showFileNameView = "showFile";
	
	
	@RequestMapping(value = FILE_UPLOAD_FORM, method=RequestMethod.POST)
	public String getUploadForm(
			@ModelAttribute(MODEL_ATTRIBUTE_UPLOADED_FILE) UploadedFile uploadedFile,
			BindingResult result) {
		return uploadFormView;
	}

	@RequestMapping(value =FILE_UPLOAD, method=RequestMethod.POST)
	public String fileUploaded(
			@ModelAttribute(MODEL_ATTRIBUTE_UPLOADED_FILE) UploadedFile uploadedFile,
			BindingResult result, Map<String, String> modelMap) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String fileName = file.getOriginalFilename();
		modelMap.put(FILE_NAME_MAP_KEY, fileName);
		if (result.hasErrors()) {
			return showFileNameView;
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

		return showFileNameView;
	}
	

}