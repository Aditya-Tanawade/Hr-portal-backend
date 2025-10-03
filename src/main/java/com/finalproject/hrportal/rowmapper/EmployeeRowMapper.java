package com.finalproject.hrportal.rowmapper;

import com.finalproject.hrportal.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        String employeeId=rs.getString("employee_id");
        String email=rs.getString("email");
        String passwordHash=rs.getString("password_hash");
        String fullName=rs.getString("full_name");
        String gender=rs.getString("gender");
        String roleKey=rs.getString("role_key");
        double ctc=rs.getDouble("ctc");
        boolean isActive=(rs.getInt("is_active")==1) ? true:false;
        int projectId=rs.getInt("project_id");
        String skills=rs.getString("skills");
        double experience=rs.getDouble("experience");
        String profileRole=rs.getString("PROFILE_ROLE");
        String createdAt=rs.getString("created_at");
        String updatedAt=rs.getString("updated_at");
        return new Employee(employeeId,email,passwordHash,fullName,gender,roleKey,ctc,isActive,projectId,skills,experience,profileRole,createdAt,updatedAt);
    }
}
