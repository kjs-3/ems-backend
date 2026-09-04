package com.example.employeemanagementSystem.departmentDto;

import com.example.employeemanagementSystem.entity.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NotNull
public class DepartmentDto {
    private Long depId;
    @NotBlank(message="Department name is Required")
    private String depName;

}
