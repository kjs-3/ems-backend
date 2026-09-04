package com.example.employeemanagementSystem.employeeService;

import com.example.employeemanagementSystem.departmentRepository.DepartmentRepository;
import com.example.employeemanagementSystem.employeeDto.EmployeeDto;
import com.example.employeemanagementSystem.employeeMapper.EmployeeMapper;
import com.example.employeemanagementSystem.employeeRepository.EmployeeRepository;
import com.example.employeemanagementSystem.entity.Department;
import com.example.employeemanagementSystem.entity.Employee;
import com.example.employeemanagementSystem.exceptionHandler.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public @Nullable List<EmployeeDto> getallemployees() {
        return (List<EmployeeDto>) employeeRepository.findAll().stream()
//                .map(employee -> EmployeeMapper.etd(employee));
                .map(EmployeeMapper::etd).toList();
    }

    public @Nullable EmployeeDto getempbyId(long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException
                                ("Employee Id is Not Found"));
        return EmployeeMapper.etd(employee);

    }

    public @Nullable EmployeeDto addemp(@Valid EmployeeDto employeeDto) {
        Department d=finddepartment(employeeDto.getDepId());
        Employee saved=employeeRepository.
                save(EmployeeMapper.dte(employeeDto,d));
        return EmployeeMapper.etd(saved);
    }
    private Department finddepartment(Long id){
        return departmentRepository.findById(id)
                .orElseThrow( ()->new ResourceNotFoundException
                        ("Department Id is Not Found"));
    }

    public @Nullable EmployeeDto updateemp(EmployeeDto employeeDto, Long id) {
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException
                                ("Employee Id is Not Found"));
        Department d=finddepartment(employeeDto.getDepId());
//        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpName(employeeDto.getEmpName());
        employee.setEmpEmail(employeeDto.getEmpEmail());
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(d);
        return EmployeeMapper.etd(employeeRepository.save(employee));
    }

    public void deletebyId(long id) {
        if(!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("EmployeeId Not Found");
        }
            employeeRepository.deleteById(id);
        }

}
