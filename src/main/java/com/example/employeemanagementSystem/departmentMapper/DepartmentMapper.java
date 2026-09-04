package com.example.employeemanagementSystem.departmentMapper;

import com.example.employeemanagementSystem.departmentDto.DepartmentDto;
import com.example.employeemanagementSystem.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    //entity to dto
    public static DepartmentDto etd(Department department){
       return new DepartmentDto(department.getDepId(),department.getDepName());
    }
    //dto to entity
    public static Department dte(DepartmentDto d){
        Department department=new Department();
//        department.setDepId(d.getDepId());
        department.setDepName(d.getDepName());
        return department;
    }
}
