package com.example.employeemanagementSystem.departmentController;

import com.example.employeemanagementSystem.departmentDto.DepartmentDto;
import com.example.employeemanagementSystem.departmentService.DepartmentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getalldepartments")
    public ResponseEntity<List<DepartmentDto>> getalldep(){
        return ResponseEntity.ok(departmentService.getalldep());
    }
    @PostMapping("/adddep")
    public ResponseEntity<DepartmentDto> adddep(@Valid @RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.adddep(departmentDto));
    }
    @DeleteMapping("/deletedep/{id}")
    public ResponseEntity<Void> deletedep(@PathVariable long id){
        departmentService.deletedep(id);
        return ResponseEntity.noContent().build();
    }
}
