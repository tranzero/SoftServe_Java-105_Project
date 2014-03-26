package com.ita.edu.softserve.manager.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

import com.ita.edu.softserve.manager.FileManager;

@Service
public class FileManagerImpl implements FileManager {
	private static final Logger LOGGER = Logger
			.getLogger(FileManagerImpl.class);
	
	private InputStream inputStream;
	private OutputStream outputStream;
	
	@Override
	public boolean deleteFile (String fileName){
		try{
		File file = new File(fileName);
		file.delete();
		return true;
		}catch(Exception e){
			 
    		LOGGER.error(e);
    		
    		throw e;
 
    	}
	}
	
	@Override
	public boolean createFile(String fileName, MultipartFile file){
		try {
			inputStream = file.getInputStream();

			File newFile = new File(fileName);
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
			return true;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

}
