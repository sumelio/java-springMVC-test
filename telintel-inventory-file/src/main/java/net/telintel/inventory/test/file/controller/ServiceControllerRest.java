package com.websystique.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.websystique.springmvc.model.File;
import com.websystique.springmvc.model.Files;

@RestController
public class ServiceControllerRest {

	private static final Logger logger = Logger.getLogger(ServiceControllerRest.class);

	private static String UPLOAD_LOCATION="/home/m/work/beitech/apache-tomcat-9.0.0.M26/";
	
	@Autowired
	private Environment env;
	
	
	   @PostMapping("/upload22"  )
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

	   logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getOriginalFilename());

 
		logger.info(" ------------------------------- >>>> " + env.getProperty("update.location"));
		
		// Now do something with file...
		try {
			FileCopyUtils.copy(file.getBytes(), new java.io.File( env.getProperty("update.location") + file.getOriginalFilename()));
		} catch (IOException e) {
			logger.error("OK>>>>>>>>>>>>>>>>>>  " + e.getMessage(), e);
		}
		String fileName = file.getOriginalFilename();
          List<File> files = new ArrayList<>();
          
          files.add( new File());
		
          return  new ResponseEntity(
				files , HttpStatus.OK);
        
    }
	
	   
	   
		 
	   @PostMapping("/upload33")
	    public ResponseEntity<Files> handleFileUpload3(@RequestParam("file") MultipartFile file,
	            RedirectAttributes redirectAttributes) {

		   logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getOriginalFilename());

	 
			logger.info(" ------------------------------- >>>> " + env.getProperty("update.location"));
			
			// Now do something with file...
			try {
				FileCopyUtils.copy(file.getBytes(), new java.io.File( env.getProperty("update.location") + file.getOriginalFilename()));
			} catch (IOException e) {
				logger.error("OK>>>>>>>>>>>>>>>>>>  " + e.getMessage(), e);
			}
			String fileName = file.getOriginalFilename();
	          List<File> listfiles = new ArrayList<>();
	          Files files = new Files();
	          files.setFiles(listfiles);
	          
			ResponseEntity<Files> list = new ResponseEntity<Files>(
					files , HttpStatus.OK);
	        return list;
	    }
	
}
