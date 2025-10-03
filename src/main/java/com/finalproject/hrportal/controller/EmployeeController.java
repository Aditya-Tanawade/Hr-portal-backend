package com.finalproject.hrportal.controller;

import com.finalproject.hrportal.domain.Employee;
import com.finalproject.hrportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping("/allemployee")
    public List<Employee> getAllEmployee(){
        return employeeRepository.getAllEmployee();
    }

    @GetMapping("/fullname/{employeeId}")
    public ResponseEntity<String>employeeName(@PathVariable ("employeeId") String employeeId){
        return ResponseEntity.ok(employeeRepository.getEmployeeName(employeeId));
    }
}
