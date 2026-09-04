package com.example.employeemanagementSystem.employeeRepository;

import com.example.employeemanagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
