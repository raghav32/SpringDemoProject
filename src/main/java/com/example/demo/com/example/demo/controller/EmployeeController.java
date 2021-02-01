package com.example.demo.com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.com.example.demo.model.Employee;
import com.example.demo.com.example.demo.service.EmployeeService;

@Controller 
@Configuration
public class EmployeeController implements WebMvcConfigurer{
	
	@Autowired
    private EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }
    
    
    
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }
     
    
    
    
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
    		BindingResult bindingResult,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {    
    	
    	if(bindingResult.hasErrors()) {
    		return "new_employee";
    	}else {
        // save employee to database
    	String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
    	employee.setLogo(fileName);
    	Employee savedEmployee=employeeService.saveEmployee(employee);
    	
    	String uploadDir="./brand-logos/"+savedEmployee.getId();
    	
    	Path uploadPath=Paths.get(uploadDir);
    	if(!Files.exists(uploadPath)) {
    		Files.createDirectories(uploadPath);
    	}
    	
    	try(InputStream inputStream =multipartFile.getInputStream()) {
    		Path filePath=uploadPath.resolve(fileName);
    		System.out.println(filePath.toFile().getAbsolutePath());
    		
    		Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
    	}catch(IOException e){
    		throw new IOException("Could not save uploaded file: "+fileName);
    	}
    	
        return "redirect:/";
    }
   }
    
    @GetMapping("/showUserImage/{id}/{logo}")
    public String getImage(@PathVariable(value="id") long id,@PathVariable(value="logo") String logo,Model model) {
    	Employee employee = employeeService.getEmployeeById(id);
    	
    	Path employeeUploadDir=Paths.get("./brand-logos");
    	String employeeUploadPath=employeeUploadDir.toFile().getAbsolutePath();
		return employeeUploadPath;
		
	}
    
   
   
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);        
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        // call delete employee method 
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
