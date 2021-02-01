package com.example.demo.com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.com.example.demo.model.Employee;
import com.example.demo.com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
	     Optional < Employee > optional = employeeRepository.findById(id);
	        Employee employee = null;
	        if (optional.isPresent()) {
	            employee = optional.get();
	        } else {
	            throw new RuntimeException(" Employee not found for id :: " + id);
	        }
	        return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		// TODO Auto-generated method stub
		  this.employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee getImage(long id, String logo) {
		
		
		return null;
	}

	

}


//  <img th:src="@{${employee.LogoImagePath}}" style="width: 100px">
//th:text="${employee.logo}"
//<a th:href="@{/showUserImage/{id}(id=${employee.id})/{logo}(id=${employee.logo})}"> </a>
