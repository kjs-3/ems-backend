package com.example.employeemanagementSystem.departmentService;

import com.example.employeemanagementSystem.departmentDto.DepartmentDto;
import com.example.employeemanagementSystem.departmentMapper.DepartmentMapper;
import com.example.employeemanagementSystem.departmentRepository.DepartmentRepository;
import com.example.employeemanagementSystem.entity.Department;
import com.example.employeemanagementSystem.exceptionHandler.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public @Nullable List<DepartmentDto> getalldep() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::etd)
                .toList();
    }

    public @Nullable DepartmentDto adddep(@Valid DepartmentDto departmentDto) {
        Department department=departmentRepository
                .save(DepartmentMapper.dte(departmentDto));
        return DepartmentMapper.etd(department);
    }

    public void deletedep(long id) {
        if(!departmentRepository.existsById(id)){
            throw new ResourceNotFoundException("DepartmentId Not exsists");
        }
        departmentRepository.deleteById(id);
    }
}
