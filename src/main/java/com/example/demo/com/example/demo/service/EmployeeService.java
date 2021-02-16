package com.example.demo.com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.com.example.demo.model.Employee;

public interface EmployeeService {
    List < Employee > getAllEmployees();
    Employee saveEmployee(Employee employee);
    
    Employee getImage(long id,String logo);
    
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField,String sortDirection);
    
}

//  @{${employee.logoImagePath}}"