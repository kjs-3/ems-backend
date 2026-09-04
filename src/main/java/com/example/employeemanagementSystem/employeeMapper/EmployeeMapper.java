package com.example.employeemanagementSystem.employeeMapper;

import com.example.employeemanagementSystem.employeeDto.EmployeeDto;
import com.example.employeemanagementSystem.entity.Department;
import com.example.employeemanagementSystem.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    // entity to Dto --->@manytoone so while giving employee obj it also gives
    //department obj
    public static EmployeeDto etd(Employee e){
        EmployeeDto empdto=new EmployeeDto();
        empdto.setEmpId(e.getEmpId());
        empdto.setEmpName(e.getEmpName());
        empdto.setEmpEmail(e.getEmpEmail());
        empdto.setSalary( e.getSalary());
        empdto.setDepId(e.getDepartment().getDepId());
        empdto.setDepName(e.getDepartment().getDepName());
        return empdto;
    }
    //dto to entity --->fetch dep obj from service class by finding depId
    //and inserting to employee
    public static Employee dte(EmployeeDto e, Department department){
        Employee emp=new Employee();
//        emp.setEmpId(e.getEmpId());
        emp.setEmpName(e.getEmpName());
        emp.setEmpEmail(e.getEmpEmail());
        emp.setSalary(e.getSalary());
        emp.setDepartment(department);
        return emp;
    }
}
