package com.example.employeemanagementSystem.employeeController;

import com.example.employeemanagementSystem.employeeDto.EmployeeDto;
import com.example.employeemanagementSystem.employeeService.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getallemployess")
    public ResponseEntity<List<EmployeeDto>> getallemployess(){
        return ResponseEntity.ok(employeeService.getallemployees());
    }
    @GetMapping("/getempbyId/{id}")
    public ResponseEntity<EmployeeDto> getempbyId(@PathVariable long id){
        return ResponseEntity.ok(employeeService.getempbyId(id));
    }
    @PostMapping("/addemployee")
    public ResponseEntity<EmployeeDto> addemp(@Valid @RequestBody EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.addemp(employeeDto));
    }
    @PutMapping("/updateemp/{id}")
    public ResponseEntity<EmployeeDto> updateemp(@RequestBody EmployeeDto employeeDto,@PathVariable long id){
        return ResponseEntity.ok(employeeService.updateemp(employeeDto,id));
    }
    @DeleteMapping("/deleteemp/{id}")
    public ResponseEntity<Void> deleteemp(@PathVariable long id){
        employeeService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }


}
