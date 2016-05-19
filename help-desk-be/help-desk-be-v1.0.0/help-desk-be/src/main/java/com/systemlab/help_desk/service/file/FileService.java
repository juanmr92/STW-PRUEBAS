package com.systemlab.help_desk.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	 void upload(MultipartFile file, String fileName) throws Exception;
	 
	 void upload(MultipartFile files[], String[] filesNames) throws Exception;

}
