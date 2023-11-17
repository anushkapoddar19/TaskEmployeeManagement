package com.EmployeeManagement.EmployeeManagement.repository;

import com.EmployeeManagement.EmployeeManagement.entities.TaxCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<TaxCalculation, Long> {
}
