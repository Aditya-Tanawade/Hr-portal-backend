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
    private String teamLeaderId;
    private String ProjectManagerId;
    private String HrId;
    private int applicationId;
    private int candidateId;
    private int jobRequestId;
    private int roundNumber;
    private InterviewStatus status;
    private String remarks;
    private double score;
    private String googleMeetLink;
    private LocalDate InterviewDate;
    private String StartTime;
    private String endTime;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
