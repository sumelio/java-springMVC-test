package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.websystique.springmvc.model.File;
import com.websystique.springmvc.model.Files;

//import com.websystique.springmvc.model.FileBucket;
//import com.websystique.springmvc.model.MultiFileBucket;
//import com.websystique.springmvc.util.FileValidator;
//import com.websystique.springmvc.util.MultiFileValidator;
import org.apache.log4j.Logger;

@Controller
public class FileUploadController {
	
	private static final Logger logger = Logger.getLogger(FileUploadController.class);

	private static String UPLOAD_LOCATION="/home/m/work/beitech/apache-tomcat-9.0.0.M26/";

//	@Autowired
//	FileValidator fileValidator;
//
//	@Autowired
//	MultiFileValidator multiFileValidator;
	
	@Autowired
	private Environment env;

//	@InitBinder("fileBucket")
//	protected void initBinderFileBucket(WebDataBinder binder) {
//		binder.setValidator(fileValidator);
//	}
//
//	@InitBinder("multiFileBucket")
//	protected void initBinderMultiFileBucket(WebDataBinder binder) {
//		binder.setValidator(multiFileValidator);
//	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "welcome";
	}

//	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
//	public String getSingleUploadPage(ModelMap model) {
//		FileBucket fileModel = new FileBucket();
//		model.addAttribute("fileBucket", fileModel);
//		return "singleFileUploader";
//	}
//
//	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST, headers = {"Accept=application/json","content-type=multipart/form-data"})
//	public String singleFileUpload(@Valid FileBucket fileBucket,
//			BindingResult result, ModelMap model) throws IOException {
//
//		if (result.hasErrors()) {
//			logger.error( "validation errors");
//			return "singleFileUploader";
//		} else {
//			logger.info("Fetching file " + fileBucket.getFile());
//	 
//			MultipartFile multipartFile = fileBucket.getFile();
//
//			
//			logger.info(" ------------------------------- >>>> " + env.getProperty("update.location"));
//			
//			// Now do something with file...
//			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( env.getProperty("update.location") + fileBucket.getFile().getOriginalFilename()));
//			String fileName = multipartFile.getOriginalFilename();
//			model.addAttribute("fileName", fileName);
//			return "success";
//		}
//	}

//	@RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
//	public String getMultiUploadPage(ModelMap model) {
//		MultiFileBucket filesModel = new MultiFileBucket();
//		model.addAttribute("multiFileBucket", filesModel);
//		return "multiFileUploader";
//	}
//	
	
	@RequestMapping(value = "/multiUpload", method = RequestMethod.GET)
	public String getMultiUploadPage() { 
		return "multiFileUploader";
	}
	
	
	@RequestMapping(value = "/executesampleservice", method = RequestMethod.POST,
		    consumes = {"multipart/form-data"})
		@ResponseBody
		public boolean executeSampleService(
		        @RequestPart("properties") @Valid String properties,
		        @RequestPart("file") @Valid @NotNull  MultipartFile file) {
		    
		logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getName());
		return file.getName().length() > 0;
		}
	

	@RequestMapping(value = "/executesampleservice2", method = RequestMethod.POST, headers = {"Accept=application/json","content-type=multipart/form-data","x-requested-with:XMLHttpRequest"} ,
		    consumes = {"multipart/form-data"})
		@ResponseBody
		public String executeSampleService2(
		        @RequestPart("file")   MultipartFile file) {
		logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getName());
		
		return "multiSuccess";
		}
	
	
	@PostMapping("/test")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
		logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getName());
		return "multiSuccess";
	}
	
	
	@PostMapping("/test2")
	public String handleFileUpload2(@RequestParam("file") MultipartFile file) {
		logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getName());
		return "multiSuccess";
	}
	
	 @RequestMapping(value="/upload", method=RequestMethod.POST)
	    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
	            @RequestParam("file") MultipartFile file){
		 
		 logger.info("OK>>>>>>>>>>>>>>>>>>  " + file.getName());
		 return "multiSuccess";
	    }
	 
	 
	   @PostMapping("/upload2")
	    public ResponseEntity<List<File >> handleFileUpload(@RequestParam("file") MultipartFile file,
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
			ResponseEntity<List<File>> list = new ResponseEntity<List<File>>(
					files , HttpStatus.OK);
	        return list;
	    }
	   
	   
		 
	   @PostMapping("/upload3")
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
	          listfiles.add(new File());
	          Files files = new Files();
	          
	          files.setFiles(listfiles);
	          
			ResponseEntity<Files> list = new ResponseEntity<Files>(
					files , HttpStatus.OK);
	        return list;
	    }

	 
	
//	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
//	public String multiFileUpload(@Valid MultiFileBucket multiFileBucket,
//			BindingResult result, ModelMap model) throws IOException {
//
//		if (result.hasErrors()) {
//			System.out.println("validation errors in multi upload");
//			return "multiFileUploader";
//		} else {
//			System.out.println("Fetching files");
//			List<String> fileNames = new ArrayList<String>();
//			// Now do something with file...
//			for (FileBucket bucket : multiFileBucket.getFiles()) {
//				FileCopyUtils.copy(bucket.getFile().getBytes(), new File(env.getProperty("update.location") + bucket.getFile().getOriginalFilename()));
//				fileNames.add(bucket.getFile().getOriginalFilename());
//			}
//
//			model.addAttribute("fileNames", fileNames);
//			return "multiSuccess";
//		}
//	}
	
	

}
