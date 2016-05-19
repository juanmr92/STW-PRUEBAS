package com.systemlab.help_desk.service.file.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.systemlab.help_desk.exception.EmptyFileException;
import com.systemlab.help_desk.exception.FileUploadException;
import com.systemlab.help_desk.service.file.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	 private static org.apache.log4j.Logger log =Logger.getLogger(FileServiceImpl.class);
	
	public void upload(MultipartFile file, String fileName) throws Exception {
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.base");
//                log.info("---------------------------------------------------------------------------------BASE---->"+System.getProperty("catalina.base"));
//                log.info("---------------------------------------------------------------------------------HOME---->"+System.getProperty("catalina.home"));
                
                
                File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "systemlab.com.pe"+ File.separator+"stw"+ File.separator + "screenshots");
                if (!dir.exists())
                    dir.mkdirs();
                
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                
                System.out.println("Server File Location="+ serverFile.getAbsolutePath());
                log.info("Server File Location="+ serverFile.getAbsolutePath());
 
            } catch (Exception e) {
            	 log.info("FILE EXCEPTION--------------------->"+e.getMessage());
                throw new FileUploadException();
            }
        } else {
        	 throw new EmptyFileException();
        }
	}
	
	public void upload(MultipartFile files[], String[] filesNames) throws Exception {

	}

	public void download() throws Exception {

	}

}
