package com.EmployeeManagement.EmployeeManagement.controller;

import com.EmployeeManagement.EmployeeManagement.entities.Employee;
import com.EmployeeManagement.EmployeeManagement.entities.TaxCalculation;
import com.EmployeeManagement.EmployeeManagement.service.EmployeeService;
import com.EmployeeManagement.EmployeeManagement.service.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeManagementController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaxCalculationService taxCalculationService;

    @PostMapping
    public ResponseEntity<Employee> storeEmployeeDetails(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/tax-deduction")
    public ResponseEntity<List<TaxCalculation>> taxDeductionForCurrentFY() {
        List<TaxCalculation> taxDeductions = taxCalculationService.taxDeductionForCurrentFY();
        return new ResponseEntity<>(taxDeductions, HttpStatus.OK);
        }
    }

