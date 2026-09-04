package com.example.employeemanagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @Column(name="empName",nullable = false)
    private String empName;
    @Column(nullable = false,name="empEmail")
    private String empEmail;
    @Column(nullable = false,name="empSalary")
    private Long salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="depId")
    private Department department;

}
