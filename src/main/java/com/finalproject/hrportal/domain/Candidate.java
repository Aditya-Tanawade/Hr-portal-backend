package com.finalproject.hrportal.domain;


import com.finalproject.hrportal.domain.enums.CandidateGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private int candidateId;
    private String email;
    private String passwordHash;
    private String fullName;
    private CandidateGender gender;
    private double expectedCtc;
    private String resumePath;
    private double totalExperience;
    private int noticePeriod;
    private String currentCompany;
    private String skills;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String profileRole;


}
