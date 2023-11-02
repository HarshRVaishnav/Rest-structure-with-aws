package com.example.service;

import org.springframework.web.multipart.MultipartFile;

public interface Fileservice {
	
	String saveFile(MultipartFile file);
	
	byte[] downloadFile(String filename);
	
	String deletefile(String fileName);
	
	

}
