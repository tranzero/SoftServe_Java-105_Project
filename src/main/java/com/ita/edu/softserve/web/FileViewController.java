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

	public static final String imageInputRequestValue = "news/images/{img}.{res}";
	public static final String pathVariableImgValue = "img";
	public static final String pathVariableResValue = "res";
	public static final String headerComtentsDispositionValue = "Content-Disposition";
	public static final String headerAttachmentValue = "attachment; filename=\"";
	public static final String headerAttachmentEndOfStringValue = "\"";

	@RequestMapping(value = imageInputRequestValue, method = RequestMethod.GET)
	public void showImg(HttpServletResponse response,
			@PathVariable(pathVariableImgValue) String img,
			@PathVariable(pathVariableResValue) String res) throws IOException {

		File file = new File(propertyManager.getImgPath() + img + "." + res);

		response.setHeader(headerComtentsDispositionValue,
				headerAttachmentValue + file.getName()
						+ headerAttachmentEndOfStringValue);
		InputStream inputStream = null;
		inputStream = new FileInputStream(file);
		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}
}
