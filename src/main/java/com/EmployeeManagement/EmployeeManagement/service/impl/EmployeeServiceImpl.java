package com.EmployeeManagement.EmployeeManagement.service.impl;

import com.EmployeeManagement.EmployeeManagement.entities.Employee;
import com.EmployeeManagement.EmployeeManagement.repository.EmployeeRepository;
import com.EmployeeManagement.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }
}
