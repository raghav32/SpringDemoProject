package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.com.example.demo.model.Employee;
import com.example.demo.com.example.demo.repository.EmployeeRepository;
import com.example.demo.com.example.demo.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void getUsersTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(376,"suresh","das","sg@gmail.com"),new Employee(958,"darshna","lakhotia","dg@gmail.com")).collect(Collectors.toList()));
		
		assertEquals(2,employeeServiceImpl.getAllEmployees().size());
		
		
	}
	
		
}
