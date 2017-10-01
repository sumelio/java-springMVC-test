package com.websystique.springmvc.configuration;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

 

 

@Configuration
@EnableWebMvc
@PropertySource({ "classpath:directoy-${envTarget:file}.properties" })
@ComponentScan(basePackages = "com.websystique.springmvc")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = Logger.getLogger(WebMvcConfigurerAdapter.class);

	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		logger.info("StandardServletMultipartResolver");
		
		return new StandardServletMultipartResolver();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(
				"/static/");
		
		registry.addResourceHandler("/libs/**").addResourceLocations("/libs/");
	    registry.addResourceHandler("/app/**").addResourceLocations("/app/");
	    registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	    registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
 
	  

}