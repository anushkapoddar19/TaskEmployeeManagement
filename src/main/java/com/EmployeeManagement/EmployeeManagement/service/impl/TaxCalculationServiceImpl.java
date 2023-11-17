package com.EmployeeManagement.EmployeeManagement.service.impl;

import com.EmployeeManagement.EmployeeManagement.entities.Employee;
import com.EmployeeManagement.EmployeeManagement.entities.TaxCalculation;
import com.EmployeeManagement.EmployeeManagement.repository.EmployeeRepository;
import com.EmployeeManagement.EmployeeManagement.repository.TaxRepository;
import com.EmployeeManagement.EmployeeManagement.service.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxCalculationServiceImpl implements TaxCalculationService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TaxRepository taxRepository;
    @Override
    public List<TaxCalculation> taxDeductionForCurrentFY() {

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Define the start and end dates of the current financial year (April to March)
        LocalDate financialYearStart = LocalDate.of(currentDate.getYear() - 1, Month.APRIL, 1);
        LocalDate financialYearEnd = LocalDate.of(currentDate.getYear(), Month.MARCH, 31);

        // Retrieve employees with DOJ within the current financial year
        List<Employee> employees = employeeRepository.findByDojBetween(financialYearStart, financialYearEnd);

        // Calculate tax deductions for each employee
        List<TaxCalculation> taxCalclation = new ArrayList<>();
        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee);
            double taxAmount = calculateTaxAmount(yearlySalary);
            double cessAmount = calculateCessAmount(yearlySalary);

            TaxCalculation taxCal = new TaxCalculation();
            taxCal.setEmployeeCode(employee.getEmployeeId());
            taxCal.setFirstName(employee.getFirstName());
            taxCal.setLastName(employee.getLastName());
            taxCal.setYearlySalary(BigDecimal.valueOf(yearlySalary));
            taxCal.setTaxAmount(BigDecimal.valueOf(taxAmount));
            taxCal.setCessAmount(BigDecimal.valueOf(cessAmount));

            taxCalclation.add(taxCal);
        }
        return taxCalclation;
    }
    private double calculateYearlySalary(Employee employee) {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int dojYear = employee.getDoj().getYear();
        int monthsWorked = 12 - employee.getDoj().getMonthValue() + 1;

        // Exclude the months before the employee's joining date in the current year
        if (currentYear == dojYear) {
            monthsWorked = 12 - employee.getDoj().getMonthValue() + 1;
        }

        double monthlySalary = employee.getSalary().doubleValue();
        double yearlySalary = monthlySalary * monthsWorked;

        return yearlySalary;

    }

    private double calculateTaxAmount(double yearlySalary) {
        double taxAmount = 0.0;

        if (yearlySalary <= 250000) {
            taxAmount = 0;
        } else if (yearlySalary <= 500000) {
            taxAmount = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            taxAmount = 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
        } else {
            taxAmount = 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
        }

        return taxAmount;
    }

    private double calculateCessAmount(double yearlySalary) {
        double cessAmount = 0.0;
        double threshold = 2500000;

        if (yearlySalary > threshold) {
            cessAmount = (yearlySalary - threshold) * 0.02;
        }

        return cessAmount;
    }
    }

