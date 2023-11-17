package com.EmployeeManagement.EmployeeManagement.service;

import com.EmployeeManagement.EmployeeManagement.entities.TaxCalculation;

import java.util.List;

public interface TaxCalculationService {

    public List<TaxCalculation> taxDeductionForCurrentFY();
}
