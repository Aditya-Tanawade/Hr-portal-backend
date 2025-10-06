package com.finalproject.hrportal.domain;

import com.finalproject.hrportal.domain.enums.InterviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interview {
    private int interviewId;
    private String interviewer_id;
    private int application_id;
    private int roundNumber;
    private InterviewStatus status;
    private String remarks;
    private double score;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String interviewLink;
}
