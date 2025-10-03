package com.finalproject.hrportal.repository;

import com.finalproject.hrportal.domain.Employee;
import com.finalproject.hrportal.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeRepository  {

    List<Employee>getAllEmployee();
    List<String>getEmployeeByRole(String role);
    List<Employee>getEmployeesOnBench();

    Integer getCountOfActiveProjects(String pmId);
    Integer getCountOfBenchEmployee();

    String getEmployeeName(String employeeId);

    List<Employee> getAllTeamMembers(int projectId);
}
