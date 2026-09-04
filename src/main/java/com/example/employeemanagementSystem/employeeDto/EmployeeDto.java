package com.example.employeemanagementSystem.employeeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    //empId,empemail,empsalary,depId,depName
    private Long empId;
    @NotBlank(message="Name is Required")
    private String empName;
    @NotBlank(message="Email is Required")
    private String empEmail;
    private Long salary;
    @NotNull(message="Enter departmentId")
    private Long depId;
    private String depName;
}
