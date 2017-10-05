package com.websystique.springmvc.configuration;

import java.io.BufferedInputStream; 
import java.util.Properties;

import javax.servlet.MultipartConfigElement; 
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger = Logger.getLogger(WebMvcConfigurerAdapter.class);
    private static final String NAME_PROPERTIES = "directoy-file.properties";
    
	 

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("===========================HelloWorldInitializer");
		return new Class[] { HelloWorldConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {

		MultipartConfigElement multipartConfigElement = null;

		Properties properties = new Properties();
		try (BufferedInputStream input = new BufferedInputStream(
				HelloWorldInitializer.class.getClassLoader().getResourceAsStream(NAME_PROPERTIES))) {

			properties.load(input);
			
			String updateLocation = properties.getProperty("update.location");
			long fileMaxFileSize = new Long(properties.getProperty("file.max.file.size")).longValue();
			long fileMaxRequestSize = new Long(properties.getProperty("file.request.size")).longValue();
			int fileSizeThreshold = new Integer(properties.getProperty("file.size.threshold")).intValue();

			logger.info("================================ updateLocation=" + updateLocation);
			multipartConfigElement = new MultipartConfigElement(updateLocation, fileMaxFileSize, fileMaxRequestSize,
					fileSizeThreshold);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return multipartConfigElement;
	}

}