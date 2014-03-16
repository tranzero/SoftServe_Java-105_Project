package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileManager {

	void deleteFile(String fileName);

	void createFile(String fileName, MultipartFile file);

}
