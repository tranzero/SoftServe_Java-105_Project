package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileManager {

	boolean deleteFile(String fileName);

	boolean createFile(String fileName, MultipartFile file);

}
