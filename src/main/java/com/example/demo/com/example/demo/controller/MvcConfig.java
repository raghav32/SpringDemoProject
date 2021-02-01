package com.example.demo.com.example.demo.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path employeeUploadDir=Paths.get("./brand-logos");
		String employeeUploadPath=employeeUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/brand-logos/**").addResourceLocations("file:/"+employeeUploadPath+"/");
	}
	
		

}
