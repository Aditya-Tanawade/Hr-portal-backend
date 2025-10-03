package com.finalproject.hrportal.repository.impl;

import com.finalproject.hrportal.domain.Employee;
import com.finalproject.hrportal.dto.EmployeeResponseDTO;
import com.finalproject.hrportal.exceptions.ResourceNotFoundException;
import com.finalproject.hrportal.repository.EmployeeRepository;
import com.finalproject.hrportal.rowmapper.EmployeeRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_EMPLOYEES="SELECT * FROM employee_details";
    private static final String GET_EMPLOYEE_BY_ROLE="SELECT * FROM employee_details WHERE role_key=?";
    @Override
    public List<Employee> getAllEmployee() {
        return jdbcTemplate.query(GET_ALL_EMPLOYEES,new EmployeeRowMapper());
    }

    @Override
    public List<String> getEmployeeByRole(String role) {
        String sql="select employee_id from employee_Details where role_key=?";
        List<String>employeeByRoleList=jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("employee_id"),role);
        if(employeeByRoleList.size()==0){
            throw (new ResourceNotFoundException("NO Employee Found By this Role " + role));
        }
        return employeeByRoleList;
    }

    @Override
    public List<Employee> getEmployeesOnBench() {
        String sql="SELECT * FROM employee_details WHERE project_id IS NULL AND is_ACTIVE=1 AND role_key NOT IN('TEAMLEAD','HR','PROJECTMANAGER')";
        return jdbcTemplate.query(sql,new EmployeeRowMapper());
    }

    @Override
    public Integer getCountOfActiveProjects(String pmId) {
        String sql="SELECT COUNT(*) FROM employee_details WHERE employee_id=? AND project_id IS NOT NULL";
        return jdbcTemplate.queryForObject(sql,Integer.class,pmId);
    }

    @Override
    public Integer getCountOfBenchEmployee() {
        String sql="SELECT Count(*) FROM employee_details WHERE project_id IS NULL AND is_ACTIVE=1 AND role_key NOT IN('TEAMLEAD','HR','PROJECTMANGER')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public String getEmployeeName(String employeeId) {
        String sql="Select full_name from employee_details where employee_id=?";
        return jdbcTemplate.queryForObject(sql,String.class,employeeId);
    }

    @Override
    public List<Employee> getAllTeamMembers(int projectId) {
        String sql="Select * From employee_details WHERE project_id=? AND ROLE_KEY NOT IN('HR','PROJECTMANAGER')";
        return jdbcTemplate.query(sql,new EmployeeRowMapper(),projectId);
    }


}
