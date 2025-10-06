package com.finalproject.hrportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortlistedCandidatesDTO {
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
    private String interviewStatus;
    private String remarks;
    private double score;
}
