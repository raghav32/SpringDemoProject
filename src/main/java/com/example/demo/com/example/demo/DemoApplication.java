package com.example.demo.com.example.demo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path employeeUploadDir=Paths.get("./brand-logos");
		String employeeUploadPath=employeeUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/brand-logos/**").addResourceLocations("file:/"+employeeUploadPath+"/");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
