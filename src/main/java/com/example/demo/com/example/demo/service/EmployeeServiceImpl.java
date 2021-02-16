package com.example.demo.com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		return employeeRepository.findAll(Sort.by("date").ascending());
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

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
		// TODO Auto-generated method stub
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
		     Sort.by(sortField).descending();
		 
		    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		    return this.employeeRepository.findAll(pageable);
		
	}
	

	

}


//  <img th:src="@{${employee.LogoImagePath}}" style="width: 100px">
//th:text="${employee.logo}"
//<a th:href="@{/showUserImage/{id}(id=${employee.id})/{logo}(id=${employee.logo})}"> </a>
