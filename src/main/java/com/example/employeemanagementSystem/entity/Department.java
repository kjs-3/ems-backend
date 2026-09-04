package com.example.employeemanagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long depId;
    @Column(name = "depName")
    private String depName;
    @OneToMany(mappedBy="department",cascade = CascadeType.ALL)
    private List<Employee> employee=new ArrayList<>();
}
