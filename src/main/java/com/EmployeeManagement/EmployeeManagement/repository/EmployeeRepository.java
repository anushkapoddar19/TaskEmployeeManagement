package com.EmployeeManagement.EmployeeManagement.repository;

import com.EmployeeManagement.EmployeeManagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDojBetween(LocalDate startDate, LocalDate endDate);
}

