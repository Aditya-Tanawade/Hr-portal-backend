package com.finalproject.hrportal.dto;


import com.finalproject.hrportal.domain.enums.CandidateGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppliedCandidatesDTO {

    private int candidateId;
    private String email;
    private String fullName;
    private String gender;
    private double expectedCtc;
    private String resumePath;
    private double totalExperience;
    private int noticePeriod;
    private String skills;
    private String profileRole;
    private int applicationId;
    private LocalDate appliedAt;
    private int jobRequestId;
    private String title;


}
