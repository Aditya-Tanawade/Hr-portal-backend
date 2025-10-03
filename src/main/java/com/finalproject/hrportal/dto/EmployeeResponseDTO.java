package com.finalproject.hrportal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private String employeeId;
    private String email;
    private String fullName;
    private String gender;
    private String roleKey;
    private double ctc;
    private boolean isActive;
    private int projectId;
    private String skills;
    private double experience;
    private String profileRole;
}
