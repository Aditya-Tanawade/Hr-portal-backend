package com.finalproject.hrportal.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private String employeeId;
    private String email;
    private String passwordHash;
    private String fullName;
    private String gender;
    private String roleKey;
    private double ctc;
    private boolean isActive;
    private int projectId;
    private String skills;
    private double experience;
    private String profileRole;
    private String createdAt;
    private String updatedAt;


}
