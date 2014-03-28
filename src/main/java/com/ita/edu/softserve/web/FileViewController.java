package com.ita.edu.softserve.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.PropertiesManager;

@Controller
public class FileViewController {

	@Autowired
	PropertiesManager propertyManager;

	private static final String IMAGE_INPUT_REQUEST_VALUE = "news/images/{img}.{res}";
	private static final String NON_IMAGE_REQUEST_VALUE = "news/images/";
	private static final String PATH_VARIABLE_IMG_VALUE = "img";
	private static final String PATH_VARIABLE_RES_VALUE = "res";
	private static final String HEADER_CONTENT_DISPOSITION_VALUE = "Content-Disposition";
	private static final String HEADER_ATTACHMENT_VALUE = "attachment; filename=\"";
	private static final String HEADER_ATTACHMENT_END_OF_STRING_VALUE = "\"";
	private static final String DEFAULT_NEWS_IMAGE = "default.jpg";

	@RequestMapping(value = IMAGE_INPUT_REQUEST_VALUE, method = RequestMethod.GET)
	public void showImg(HttpServletResponse response,
			@PathVariable(PATH_VARIABLE_IMG_VALUE) String img,
			@PathVariable(PATH_VARIABLE_RES_VALUE) String res) throws IOException {

		File file = new File(propertyManager.getImgPath() + img + "." + res);
		
		if (!file.exists()) {
			file = new File(propertyManager.getImgPath() + DEFAULT_NEWS_IMAGE);
		
		} 
		response.setHeader(HEADER_CONTENT_DISPOSITION_VALUE,
				HEADER_ATTACHMENT_VALUE + file.getName()
						+ HEADER_ATTACHMENT_END_OF_STRING_VALUE);
		InputStream inputStream = null;
		inputStream = new FileInputStream(file);
		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}
	
	@RequestMapping(value = NON_IMAGE_REQUEST_VALUE, method = RequestMethod.GET)
	public void showNonImg(HttpServletResponse response
			) throws IOException {

		File file = new File(propertyManager.getImgPath() + DEFAULT_NEWS_IMAGE);

		response.setHeader(HEADER_CONTENT_DISPOSITION_VALUE,
				HEADER_ATTACHMENT_VALUE + file.getName()
						+ HEADER_ATTACHMENT_END_OF_STRING_VALUE);
		InputStream inputStream = null;
		inputStream = new FileInputStream(file);
		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}
}
